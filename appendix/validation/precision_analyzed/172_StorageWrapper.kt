package jp.toastkid.yobidashi.libs.storage

import android.content.Context
import android.net.Uri

import java.io.File
import java.util.regex.Pattern

/**
 * Initialize with context.
 *
 * @param context
 * @param dirName
 * @author toastkidjp
 */
sealed class StorageWrapper(context: Context, dirName: String) {

    /**
     * Directory object.
     */
    private val dir: File

    init {
        dir = File(getDir(context), dirName)
        if (!dir.exists()) {
            dir.mkdirs()
        }
    }

    /**
     * Subclass should be override this method.
     *
     * @param context [Context]
     */
    protected abstract fun getDir(context: Context): File

    /**
     * Get file object.
     * @param index
     *
     * @return File object
     */
    operator fun get(index: Int): File? {
        if (index < 0 || index > listFiles().size) {
            return null
        }
        return listFiles()[index]
    }

    /**
     * Remove item which specified position.
     *
     * @param index
     */
    fun removeAt(index: Int): Boolean {
        if (index < 0 || index > listFiles().size) {
            return false
        }
        return listFiles()[index].delete()
    }

    /**
     * Delete all files.
     */
    fun clean() = dir.listFiles().forEach { it.delete() }

    /**
     * Assign new file.
     * @param uri
     *
     * @return [File]
     */
    fun assignNewFile(uri: Uri): File = assignNewFile(File(uri.toString()).name)

    /**
     * Assign new file.
     * @param name
     *
     * @return [File]
     */
    fun assignNewFile(name: String): File {
        val matcher = ILLEGAL_FILE_NAME_CHARACTER.matcher(name)
        if (matcher.find()) {
            return File(dir, matcher.replaceAll("_"))
        }
        return File(dir, name)
    }

    /**
     * Internal method.
     * @return
     */
    private fun listFiles(): Array<File> = dir.listFiles()

    /**
     * Get file count.
     * @return file count
     */
    val count: Int
        get() = listFiles().size

    companion object {

        /** Illegal file name character.  */
        private val ILLEGAL_FILE_NAME_CHARACTER = Pattern.compile("[\\\\|/|:|\\*|\\?|\"|<|>|\\|]+", Pattern.DOTALL)
    }
}

/**
 * App's cache directory wrapper.
 *
 * @param context
 * @param dirName
 * @author toastkidjp
 */
class CacheDir(context: Context, dirName: String) : StorageWrapper(context, dirName) {

    override fun getDir(context: Context): File = context.cacheDir
}

/**
 * FilesDir's wrapper.
 *
 * @param context
 * @param dirName
 *
 * @author toastkidjp
 */
class FilesDir(context: Context, dirName: String) : StorageWrapper(context, dirName) {

    override fun getDir(context: Context): File = context.filesDir
}
