package fr.chenry.android.freshrss.store.database.models

import android.graphics.Bitmap
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import com.squareup.picasso.Picasso
import fr.chenry.android.freshrss.R
import fr.chenry.android.freshrss.store.api.models.SubscriptionApiItem
import fr.chenry.android.freshrss.utils.nullIfBlank
import io.reactivex.Flowable
import org.joda.time.LocalDateTime

typealias Subscriptions = List<Subscription>

@Entity(tableName = "subscriptions")
data class Subscription(
    @PrimaryKey
    val id: String,
    val title: String,
    val iconUrl: String,
    val unreadCount: Int = 0,
    val subscriptionCategories: List<String> = listOf(),
    val newestArticleDate: LocalDateTime = LocalDateTime(0)
) : BaseObservable() {
    @get:Bindable
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var imageBitmap: Bitmap? = null
        set(value) {
            value?.also {
                field = value
                notifyPropertyChanged(BR.imageBitmap)
            }
        }

    fun fetchImage(): Bitmap = Picasso.get().load(iconUrl).resizeDimen(R.dimen.icon_size, R.dimen.icon_size).get()

    companion object {
        fun fromSubscriptionApiItem(subscriptionApiItem: SubscriptionApiItem) = Subscription(
            subscriptionApiItem.id,
            subscriptionApiItem.title.trim(),
            subscriptionApiItem.iconUrl,
            subscriptionCategories = subscriptionApiItem.categories.mapNotNull { it.id.nullIfBlank() }
        )
    }
}

fun Subscription?.areSimilar(other: Subscription?): Boolean {
    if (this == null && other == null) return true
    if (this == null || other == null) return false
    return this.title == other.title &&
        this.iconUrl == other.iconUrl &&
        this.subscriptionCategories == other.subscriptionCategories
}

@Dao
interface SubscriptionsDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAll(subscriptions: Subscriptions)

    @Query("UPDATE subscriptions SET title = :title, iconUrl = :iconUrl, subscriptionCategories = :subs WHERE id = :id")
    fun updateValues(id: String, title: String, iconUrl: String, subs: List<String>)

    @Query("SELECT id FROM subscriptions")
    fun getAllIds(): Flowable<List<String>>

    @Query("SELECT * FROM subscriptions")
    fun getAll(): Flowable<Subscriptions>

    @Query("SELECT * FROM subscriptions WHERE unreadCount > 0")
    fun getAllUnread(): Flowable<Subscriptions>

    @Query("SELECT * FROM subscriptions WHERE id = :id")
    fun byId(id: String): Flowable<Subscriptions>

    @Query("SELECT * FROM subscriptions WHERE subscriptionCategories LIKE :matchRequest")
    fun bySubscriptionCategory(matchRequest: String): Flowable<Subscriptions>

    @Query("SELECT * FROM subscriptions WHERE subscriptionCategories LIKE :matchRequest AND unreadCount > 0")
    fun bySubscriptionCategoryAndUnreadCount(matchRequest: String): Flowable<Subscriptions>

    @Query("SELECT * FROM subscriptions WHERE LENGTH(imageBitmap) IS NULL")
    fun withImageToUpdate(): Flowable<Subscriptions>

    @Query("DELETE FROM subscriptions WHERE id IN (:ids)")
    fun deleteAllById(ids: List<String>)

    @Query("UPDATE subscriptions SET unreadCount = :count WHERE id = :id")
    fun updateCount(id: String, count: Int)

    @Query("UPDATE subscriptions SET newestArticleDate = :d WHERE id = :id AND newestArticleDate < :d")
    fun updateNewestArticleDate(id: String, d: LocalDateTime)

    @Query("UPDATE subscriptions SET unreadCount = unreadCount + 1 WHERE id = :id")
    fun incrementCount(id: String)

    @Query("UPDATE subscriptions SET unreadCount = unreadCount - 1 WHERE id = :id AND unreadCount > 0")
    fun decrementCount(id: String)

    @Query("UPDATE subscriptions SET imageBitmap = :bitmap WHERE id = :id")
    fun insertImage(id: String, bitmap: Bitmap)

    @Query("UPDATE subscriptions SET imageBitmap = NULL WHERE id = :id")
    fun deleteImage(id: String)
}
