package moe.feng.nhentai.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.util.Log
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import moe.feng.nhentai.R
import moe.feng.nhentai.api.ApiConstants
import java.text.DateFormat
import java.util.*

@Entity(tableName = Book.TAG) class Book: History.KeyContainer<Book.HistoryKey> {

	// Serializable fields
	@Expose @PrimaryKey @SerializedName("id") lateinit var bookId: String
	@Expose @SerializedName("media_id") lateinit var galleryId: String
	private @Expose @SerializedName("title") var titles: BookTitle = BookTitle()
	@Expose @SerializedName("upload_date") var uploadDate: Long = 0L
	@Expose @SerializedName("num_favorites") var favCount: Int = 0
	@Expose var scanlator: String = ""
	@Expose @SerializedName("num_pages") var pageCount: Int = 0
	private @Expose @SerializedName("images") var _images: BookImages = BookImages()
	@Expose @SerializedName("tags") var allTags: Array<Tag> = emptyArray()

	// Favourite mark
	@Expose var isFavourite: Boolean = false

	// Methods
	val images: BookImages get() = _images.apply { book = this@Book }

	val language: String get() = allTags.find {
		it.type == Tag.TYPE_LANGUAGE && it.name != Tag.LANG_TRANSLATED }?.name ?: Tag.LANG_JAPANESE
	val tags: Array<Tag> get() = allTags.filter { it.type == Tag.TYPE_TAG }.toTypedArray()
	val categories: Array<Tag> get() = allTags.filter { it.type == Tag.TYPE_CATEGORY }.toTypedArray()
	val characters: Array<Tag> get() = allTags.filter { it.type == Tag.TYPE_CHARACTER }.toTypedArray()
	val groups: Array<Tag> get() = allTags.filter { it.type == Tag.TYPE_GROUP }.toTypedArray()
	val parodies: Array<Tag> get() = allTags.filter { it.type == Tag.TYPE_PARODY }.toTypedArray()
	val artists: Array<Tag> get() = allTags.filter { it.type == Tag.TYPE_ARTIST }.toTypedArray()

	val title: String get() = titles.english
	val jpTitle: String? get() = titles.japanese
	val prettyTitle: String? get() = titles.pretty

	fun getLanguageFlagRes(): Int = when (language) {
		Tag.LANG_CHINESE -> R.drawable.ic_lang_cn
		Tag.LANG_ENGLISH -> R.drawable.ic_lang_gb
		Tag.LANG_JAPANESE -> R.drawable.ic_lang_jp
		else -> 0
	}

	fun getFormattedTime(): String = DateFormat.getDateTimeInstance().format(Date(uploadDate * 1000))

	override fun getHistoryAction(): Int = History.ACTION_READ_BOOK

	override fun getHistoryKey(): Book.HistoryKey
			= HistoryKey(bookId, galleryId, titles, images.cover)

	// Sub-classes

	data class BookTitle(
			@Expose var japanese: String? = null,
			@Expose var pretty: String? = null,
			@Expose var english: String = ""
	)

	class BookImages(
			@Expose var cover: Picture? = null,
			@Expose var pages: Array<Picture> = emptyArray(),
			@Expose var thumbnail: Picture? = null,
			var book: Book? = null
	) {

		fun getThumbnails(): Array<PictureUrl> {
			book?.let {
				val list = mutableListOf<PictureUrl>()
				for (i in 1..it.pageCount) {
					list += PictureUrl(it.pageThumbnails[i], title = i.toString())
				}
				return list.toTypedArray()
			}
			return emptyArray()
		}

	}

	class HistoryKey(
			@Expose val id: String,
			@Expose val galleryId: String,
			@Expose val titles: BookTitle,
			@Expose val cover: Picture? = null
	): History.Key {
		override fun id(): String = id
	}

	companion object {

		const val TAG = "Book"

	}

	// Extend function

	val requestUrl: String get() = ApiConstants.getBookDetailsUrl(bookId)
	val thumbUrl: String get() = ApiConstants.getBookThumbUrl(galleryId, images.cover?.fileType)
	val bigCoverUrl: String get() = ApiConstants.getBigCoverUrl(galleryId)
	val pagePictures: BookPageGetter get() = BookPageGetter(this, false)
	val pageThumbnails: BookPageGetter get() = BookPageGetter(this, true)

	val relatedApiUrl: String get() = ApiConstants.getBookRecommendUrl(bookId)

	class BookPageGetter internal constructor(private val book: Book, private val thumb: Boolean) {

		operator fun get(pageNum: Int): String =
				(if (!thumb) ApiConstants::getPictureUrl else ApiConstants::getThumbPictureUrl)(
						book.galleryId,
						pageNum.toString(),
						book.images.pages[pageNum - 1].fileType
				)

	}

}