package com.keylesspalace.tusky.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.os.Parcelable
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import androidx.core.content.ContextCompat
import com.keylesspalace.tusky.R
import com.keylesspalace.tusky.appstore.EventHub
import com.keylesspalace.tusky.appstore.StatusComposedEvent
import com.keylesspalace.tusky.appstore.StatusScheduledEvent
import com.keylesspalace.tusky.db.AccountEntity
import com.keylesspalace.tusky.db.AccountManager
import com.keylesspalace.tusky.db.AppDatabase
import com.keylesspalace.tusky.di.Injectable
import com.keylesspalace.tusky.entity.NewPoll
import com.keylesspalace.tusky.entity.NewStatus
import com.keylesspalace.tusky.entity.Status
import com.keylesspalace.tusky.network.MastodonApi
import com.keylesspalace.tusky.util.SaveTootHelper
import com.keylesspalace.tusky.util.randomAlphanumericString
import dagger.android.AndroidInjection
import kotlinx.android.parcel.Parcelize
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SendTootService : Service(), Injectable {

    @Inject
    lateinit var mastodonApi: MastodonApi
    @Inject
    lateinit var accountManager: AccountManager
    @Inject
    lateinit var eventHub: EventHub
    @Inject
    lateinit var database: AppDatabase

    private lateinit var saveTootHelper: SaveTootHelper

    private val tootsToSend = ConcurrentHashMap<Int, TootToSend>() //#inference
    private val sendCalls = ConcurrentHashMap<Int, Call<Status>>() //#inference

    private val timer = Timer() //#inference

    private val notificationManager by lazy { getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager } //#inference,property_delegation,lambda

    override fun onCreate() {
        AndroidInjection.inject(this)
        saveTootHelper = SaveTootHelper(database.tootDao(), this)
        super.onCreate()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        if (intent.hasExtra(KEY_TOOT)) {
            val tootToSend = intent.getParcelableExtra<TootToSend>(KEY_TOOT) //#inference,string_template
                    ?: throw IllegalStateException("SendTootService started without $KEY_TOOT extra")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(CHANNEL_ID, getString(R.string.send_toot_notification_channel_name), NotificationManager.IMPORTANCE_LOW) //#inference
                notificationManager.createNotificationChannel(channel)

            }

            var notificationText = tootToSend.warningText //#inference
            if (notificationText.isBlank()) {
                notificationText = tootToSend.text
            }

            val builder = NotificationCompat.Builder(this, CHANNEL_ID) //#inference
                    .setSmallIcon(R.drawable.ic_notify)
                    .setContentTitle(getString(R.string.send_toot_notification_title))
                    .setContentText(notificationText)
                    .setProgress(1, 0, true)
                    .setOngoing(true)
                    .setColor(ContextCompat.getColor(this, R.color.tusky_blue))
                    .addAction(0, getString(android.R.string.cancel), cancelSendingIntent(sendingNotificationId))

            if (tootsToSend.size == 0 || Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ServiceCompat.stopForeground(this, ServiceCompat.STOP_FOREGROUND_DETACH)
                startForeground(sendingNotificationId, builder.build())
            } else {
                notificationManager.notify(sendingNotificationId, builder.build())
            }

            tootsToSend[sendingNotificationId] = tootToSend
            sendToot(sendingNotificationId--)

        } else {

            if (intent.hasExtra(KEY_CANCEL)) {
                cancelSending(intent.getIntExtra(KEY_CANCEL, 0))
            }

        }

        return START_NOT_STICKY

    }

    private fun sendToot(tootId: Int) {

        // when tootToSend == null, sending has been canceled
        val tootToSend = tootsToSend[tootId] ?: return //#inference

        // when account == null, user has logged out, cancel sending
        val account = accountManager.getAccountById(tootToSend.accountId) //#inference

        if (account == null) {
            tootsToSend.remove(tootId)
            notificationManager.cancel(tootId)
            stopSelfWhenDone()
            return
        }

        tootToSend.retries++

        val newStatus = NewStatus( //#inference
                tootToSend.text,
                tootToSend.warningText,
                tootToSend.inReplyToId,
                tootToSend.visibility,
                tootToSend.sensitive,
                tootToSend.mediaIds,
                tootToSend.scheduledAt,
                tootToSend.poll
        )

        val sendCall = mastodonApi.createStatus( //#inference
                "Bearer " + account.accessToken,
                account.domain,
                tootToSend.idempotencyKey,
                newStatus
        )


        sendCalls[tootId] = sendCall

        val callback = object : Callback<Status> { //#inference
            override fun onResponse(call: Call<Status>, response: Response<Status>) {

                val scheduled = !tootToSend.scheduledAt.isNullOrEmpty() //#inference
                tootsToSend.remove(tootId)

                if (response.isSuccessful) {
                    // If the status was loaded from a draft, delete the draft and associated media files.
                    if (tootToSend.savedTootUid != 0) {
                        saveTootHelper.deleteDraft(tootToSend.savedTootUid)
                    }

                    if (scheduled) {
                        response.body()?.let(::StatusScheduledEvent)?.let(eventHub::dispatch) //#safe_call,safe_call
                    } else {
                        response.body()?.let(::StatusComposedEvent)?.let(eventHub::dispatch) //#safe_call,safe_call
                    }

                    notificationManager.cancel(tootId)

                } else {
                    // the server refused to accept the toot, save toot & show error message
                    saveTootToDrafts(tootToSend)

                    val builder = NotificationCompat.Builder(this@SendTootService, CHANNEL_ID) //#inference
                            .setSmallIcon(R.drawable.ic_notify)
                            .setContentTitle(getString(R.string.send_toot_notification_error_title))
                            .setContentText(getString(R.string.send_toot_notification_saved_content))
                            .setColor(ContextCompat.getColor(this@SendTootService, R.color.tusky_blue))

                    notificationManager.cancel(tootId)
                    notificationManager.notify(errorNotificationId--, builder.build())

                }

                stopSelfWhenDone()

            }

            override fun onFailure(call: Call<Status>, t: Throwable) {
                var backoff = TimeUnit.SECONDS.toMillis(tootToSend.retries.toLong()) //#inference
                if (backoff > MAX_RETRY_INTERVAL) {
                    backoff = MAX_RETRY_INTERVAL
                }

                timer.schedule(object : TimerTask() { 
                    override fun run() {
                        sendToot(tootId)
                    }
                }, backoff)
            }
        }

        sendCall.enqueue(callback)

    }

    private fun stopSelfWhenDone() {

        if (tootsToSend.isEmpty()) {
            ServiceCompat.stopForeground(this@SendTootService, ServiceCompat.STOP_FOREGROUND_REMOVE)
            stopSelf()
        }
    }

    private fun cancelSending(tootId: Int) {
        val tootToCancel = tootsToSend.remove(tootId) //#inference
        if (tootToCancel != null) {
            val sendCall = sendCalls.remove(tootId) //#inference
            sendCall?.cancel() //#safe_call

            saveTootToDrafts(tootToCancel)

            val builder = NotificationCompat.Builder(this@SendTootService, CHANNEL_ID) //#inference
                    .setSmallIcon(R.drawable.ic_notify)
                    .setContentTitle(getString(R.string.send_toot_notification_cancel_title))
                    .setContentText(getString(R.string.send_toot_notification_saved_content))
                    .setColor(ContextCompat.getColor(this@SendTootService, R.color.tusky_blue))

            notificationManager.notify(tootId, builder.build())

            timer.schedule(object : TimerTask() { 
                override fun run() {
                    notificationManager.cancel(tootId)
                    stopSelfWhenDone()
                }
            }, 5000)

        }
    }

    private fun saveTootToDrafts(toot: TootToSend) {

        saveTootHelper.saveToot(toot.text,
                toot.warningText,
                toot.savedJsonUrls,
                toot.mediaUris,
                toot.mediaDescriptions,
                toot.savedTootUid,
                toot.inReplyToId,
                toot.replyingStatusContent,
                toot.replyingStatusAuthorUsername,
                Status.Visibility.byString(toot.visibility),
                toot.poll)
    }

    private fun cancelSendingIntent(tootId: Int): PendingIntent {

        val intent = Intent(this, SendTootService::class.java) //#inference

        intent.putExtra(KEY_CANCEL, tootId)

        return PendingIntent.getService(this, tootId, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }


    companion object { //#companion

        private const val KEY_TOOT = "toot" //#inference
        private const val KEY_CANCEL = "cancel_id" //#inference
        private const val CHANNEL_ID = "send_toots" //#inference

        private val MAX_RETRY_INTERVAL = TimeUnit.MINUTES.toMillis(1) //#inference

        private var sendingNotificationId = -1 // use negative ids to not clash with other notis //#inference
        private var errorNotificationId = Int.MIN_VALUE // use even more negative ids to not clash with other notis //#inference

        @JvmStatic
        fun sendTootIntent(context: Context,
                           text: String,
                           warningText: String,
                           visibility: Status.Visibility,
                           sensitive: Boolean,
                           mediaIds: List<String>,
                           mediaUris: List<Uri>,
                           mediaDescriptions: List<String>,
                           scheduledAt: String?,
                           inReplyToId: String?,
                           poll: NewPoll?,
                           replyingStatusContent: String?,
                           replyingStatusAuthorUsername: String?,
                           savedJsonUrls: String?,
                           account: AccountEntity,
                           savedTootUid: Int
        ): Intent {
            val intent = Intent(context, SendTootService::class.java) //#inference

            val idempotencyKey = randomAlphanumericString(16) //#inference

            val tootToSend = TootToSend(text, //#inference
                    warningText,
                    visibility.serverString(),
                    sensitive,
                    mediaIds,
                    mediaUris.map { it.toString() }, //#lambda
                    mediaDescriptions,
                    scheduledAt,
                    inReplyToId,
                    poll,
                    replyingStatusContent,
                    replyingStatusAuthorUsername,
                    savedJsonUrls,
                    account.id,
                    savedTootUid,
                    idempotencyKey,
                    0)

            intent.putExtra(KEY_TOOT, tootToSend)

            if(mediaUris.isNotEmpty()) {
                // forward uri permissions
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                val uriClip = ClipData( //#inference
                        ClipDescription("Toot Media", arrayOf("image/*", "video/*")),
                        ClipData.Item(mediaUris[0]) //#inference
                )
                mediaUris
                        .drop(1)
                        .forEach { mediaUri -> //#lambda
                            uriClip.addItem(ClipData.Item(mediaUri))
                        }

                intent.clipData = uriClip

            }

            return intent
        }

    }
}

@Parcelize
data class TootToSend(val text: String, //#data_class
                      val warningText: String,
                      val visibility: String,
                      val sensitive: Boolean,
                      val mediaIds: List<String>,
                      val mediaUris: List<String>,
                      val mediaDescriptions: List<String>,
                      val scheduledAt: String?,
                      val inReplyToId: String?,
                      val poll: NewPoll?,
                      val replyingStatusContent: String?,
                      val replyingStatusAuthorUsername: String?,
                      val savedJsonUrls: String?,
                      val accountId: Long,
                      val savedTootUid: Int,
                      val idempotencyKey: String,
                      var retries: Int) : Parcelable
