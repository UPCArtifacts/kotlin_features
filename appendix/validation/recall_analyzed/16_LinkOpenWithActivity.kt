/*
 * Copyright 2015-2019 The twitlatte authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.moko256.twitlatte

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.TextViewCompat
import com.github.moko256.latte.client.base.entity.AccessToken
import com.github.moko256.latte.client.twitter.CLIENT_TYPE_TWITTER
import com.github.moko256.twitlatte.intent.excludeOwnApp
import com.github.moko256.twitlatte.repository.KEY_ACCOUNT_KEY_LINK_OPEN
import com.github.moko256.twitlatte.text.TwitterStringUtils
import com.github.moko256.twitlatte.view.dpToPx
import java.util.*

/**
 * Created by moko256 on 2017/04/16.
 *
 * @author moko256
 */

class LinkOpenWithActivity : Activity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val switchIntent = switchIntent() //#inference
        if (switchIntent != null) {
            val toName = switchIntent.component?.className  //#inference,safe_call
            if (ShowUserActivity::class.java.name != toName && ShowTweetActivity::class.java.name != toName) {
                startActivity(switchIntent)
                finish()
            } else {
                showDialog { //#lambda
                    startActivity(switchIntent.setAccountKey(it))

                    Toast.makeText(
                            this@LinkOpenWithActivity,
                            TwitterStringUtils.plusAtMark(
                                    it.screenName,
                                    it.url
                            ),
                            Toast.LENGTH_SHORT
                    ).show()

                    finish()
                }
            }
        }
    }

    private fun switchIntent(): Intent? {

        val intent = intent //#inference
        if (intent != null && (intent.action != null || intent.action != Intent.ACTION_MAIN)) {

            if (intent.action == Intent.ACTION_VIEW) {
                intent.data?.let { data -> //#safe_call,lambda
                    try {
                        when (data.scheme) { //#when_expr
                            "twitter" -> when (data.host) { //#when_expr
                                "post" -> {
                                    return PostActivity.getIntent(
                                            this,
                                            data
                                                    .getQueryParameter("in_reply_to_status_id")
                                                    ?.toLong() //#safe_call
                                                    ?: -1,
                                            data.getQueryParameter("message")
                                    )
                                }
                                "status" -> return ShowTweetActivity.getIntent(
                                        this,
                                        data.getQueryParameter("id")?.toLong() ?: -1 //#safe_call
                                )
                                "user" -> {
                                    val userId = data.getQueryParameter("id") //#inference
                                    return if (userId != null) {
                                        ShowUserActivity.getIntent(this, userId.toLong())
                                    } else {
                                        ShowUserActivity.getIntent(this, data.getQueryParameter("screen_name"))
                                    }
                                }
                                else -> return generateAlterIntent(data)
                            }
                            "https", "http" -> {
                                val pathSegments = data.pathSegments //#inference

                                if (pathSegments.size > 0 && pathSegments[0] == "i") {
                                    return generateAlterIntent(data)
                                }

                                when (pathSegments.size) { //#when_expr
                                    1 -> return when (pathSegments[0]) { //#when_expr
                                        "share" -> generatePostIntent(data)
                                        "search" -> SearchResultActivity.getIntent(this, data.getQueryParameter("q"))
                                        else -> ShowUserActivity
                                                .getIntent(this, pathSegments[0])
                                    }
                                    2 -> if (pathSegments[0] == "intent" && pathSegments[1] == "tweet") {
                                        return generatePostIntent(data)
                                    } else if (pathSegments[0] == "hashtag") {
                                        return SearchResultActivity.getIntent(this, "#${pathSegments[1]}") //#string_template
                                    }

                                    3, 5 -> {
                                        if (pathSegments[1] == "status" || pathSegments[1] == "statuses") {
                                            return ShowTweetActivity
                                                    .getIntent(this, pathSegments[2].toLong())

                                        }
                                    }
                                }

                                return if (data.getQueryParameter("status") != null) {
                                    PostActivity.getIntent(this, data.getQueryParameter("status"))
                                } else {
                                    generateAlterIntent(data)
                                }

                            }
                            "web+mastodon" -> if (data.host == "share") {
                                return PostActivity.getIntent(
                                        this,
                                        data.getQueryParameter("text")
                                )
                            }
                        }
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                }
            } else {
                intent.extras?.let { extras -> //#safe_call,lambda
                    try {
                        val text = extras.getCharSequence(Intent.EXTRA_TEXT) //#inference
                        val subject = extras.getCharSequence(Intent.EXTRA_SUBJECT) //#inference
                        val list: ArrayList<Uri>? = extras.takeIf { it.containsKey(Intent.EXTRA_STREAM) }?.let { //#lambda,safe_call,lambda
                            if (intent.action == Intent.ACTION_SEND_MULTIPLE) {
                                extras.getParcelableArrayList(Intent.EXTRA_STREAM)
                            } else {
                                extras.getParcelable<Uri?>(Intent.EXTRA_STREAM)?.let { uri -> //#safe_call,lambda
                                    arrayListOf(uri)
                                }
                            }
                        }

                        val rawText = if (text != null || subject != null) { //#inference
                            StringBuilder().apply { //#lambda
                                if (subject != null) {
                                    append(subject)
                                    append(' ')
                                }
                                if (text != null) {
                                    append(text)
                                }
                            }.toString()
                        } else {
                            null
                        }

                        if (rawText != null || list != null) {
                            return PostActivity.getIntent(this, -1, rawText, list)
                        }
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                }
            }

        }

        return null
    }

    private fun generatePostIntent(data: Uri): Intent {
        val tweet = StringBuilder(data.getQueryParameter("text") ?: "") //#inference

        data.getQueryParameter("url")?.let { //#safe_call,lambda
            tweet.append(' ').append(it)
        }

        data.getQueryParameter("hashtags")
                ?.split(",") //#safe_call
                ?.forEach { //#lambda
                    tweet.append(" #").append(it)
                }

        data.getQueryParameter("via")?.let { //#safe_call,lambda
            tweet.append(" via @").append(it)
        }

        data.getQueryParameter("related")
                ?.split(",") //#safe_call
                ?.forEach { //#lambda
                    tweet.append(" @").append(it)
                }

        return PostActivity.getIntent(
                this,
                data.getQueryParameter("in-reply-to")?.toLong() ?: -1, //#safe_call
                tweet.toString()
        )
    }

    @SuppressLint("SetTextI18n")
    private fun showDialog(callback: (AccessToken) -> Unit) {
        val accessTokens = getAccountsModel() //#inference
                .getAccessTokensByType(CLIENT_TYPE_TWITTER)
        when { //#when_expr
            accessTokens.isEmpty() -> {
                startActivity(generateAlterIntent(intent.data!!)) //#unsafe_call
                finish()
            }

            accessTokens.size == 1 -> callback(accessTokens[0])

            else -> {
                val accountsLinkOpenWith = preferenceRepository //#inference
                        .getString(KEY_ACCOUNT_KEY_LINK_OPEN, "-1")
                        .takeIf { it != "-1" } //#lambda
                        ?.run { //#safe_call,lambda
                            accessTokens.single { it.getKeyString() == this } //#lambda
                        }
                if (accountsLinkOpenWith == null) {
                    val dp = resources.displayMetrics.density //#inference
                    val dp16 = dpToPx(16, dp) //#inference
                    val dp24 = dpToPx(24, dp) //#inference
                    AlertDialog.Builder(this)
                            .setTitle(R.string.open_with_accounts)
                            .setView(TextView(this).apply { //#lambda
                                text = getString(R.string.settable_account_always_use_in_settings) + "\n" + getString(R.string.app_name)
                                TextViewCompat.setTextAppearance(this, R.style.TextAppearance_MaterialComponents_Subtitle1)
                                setPadding(dp24, dp16, dp24, dp16)
                            })
                            .setOnCancelListener { finish() } //#lambda
                            .setItems(
                                    accessTokens
                                            .map { //#lambda
                                                TwitterStringUtils.plusAtMark(it.screenName, it.url).apply { //#lambda
                                                    if (it == getCurrentClient()?.accessToken) { //#safe_call
                                                        insert(0, "Now: ")
                                                    }
                                                }
                                            }
                                            .toTypedArray()
                            ) { _, which -> //#lambda
                                callback(accessTokens[which])
                            }
                            .show()
                } else {
                    callback(accountsLinkOpenWith)
                }
            }
        }
    }

    private fun generateAlterIntent(uri: Uri): Intent {
        return Intent(Intent.ACTION_VIEW, uri).excludeOwnApp(this, packageManager)
    }
}
