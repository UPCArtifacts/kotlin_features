package info.dvkr.screenstream.data.image

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.PixelFormat
import android.graphics.Point
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.Image
import android.media.ImageReader
import android.media.projection.MediaProjection
import android.os.Handler
import android.os.HandlerThread
import android.os.Process
import android.util.DisplayMetrics
import android.view.Display
import android.view.Surface
import com.elvishew.xlog.XLog
import info.dvkr.screenstream.data.model.AppError
import info.dvkr.screenstream.data.model.FatalError
import info.dvkr.screenstream.data.model.FixableError
import info.dvkr.screenstream.data.other.getLog
import info.dvkr.screenstream.data.settings.Settings
import info.dvkr.screenstream.data.settings.SettingsReadOnly
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.SendChannel
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference

class BitmapCapture(
    private val display: Display,
    private val settingsReadOnly: SettingsReadOnly,
    private val mediaProjection: MediaProjection,
    private val outBitmapChannel: SendChannel<Bitmap>,
    private val onError: (AppError) -> Unit
) {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable -> //#coroutine,inference,lambda
        XLog.e(getLog("onCoroutineException"), throwable)
        onError(FatalError.CoroutineException)
    }

    private val coroutineScope = CoroutineScope(Job() + Dispatchers.Default + coroutineExceptionHandler) //#inference

    private enum class State { INIT, STARTED, DESTROYED, ERROR }

    @Volatile
    private var state: State = State.INIT

    private val imageThread: HandlerThread by lazy { //#property_delegation,lambda
        HandlerThread("BitmapCapture", Process.THREAD_PRIORITY_BACKGROUND)
    }
    private val imageThreadHandler: Handler by lazy { Handler(imageThread.looper) } //#property_delegation,lambda

    private var imageListener: ImageListener? = null
    private var imageReader: ImageReader? = null
    private var virtualDisplay: VirtualDisplay? = null

    private val matrix = AtomicReference<Matrix>(Matrix()) //#inference
    private val resizeFactor = AtomicInteger(Settings.Values.RESIZE_DISABLED) //#inference
    private val rotation = AtomicInteger(Settings.Values.ROTATION_0) //#inference

    private val settingsListener = object : SettingsReadOnly.OnSettingsChangeListener { //#inference,lambda
        override fun onSettingsChanged(key: String) {
            when (key) { //#when_expr
                Settings.Key.RESIZE_FACTOR, Settings.Key.ROTATION ->
                    setMatrix(settingsReadOnly.resizeFactor, settingsReadOnly.rotation)
            }
        }
    }

    init {
        XLog.d(getLog("init"))
        settingsReadOnly.registerChangeListener(settingsListener)
        setMatrix(settingsReadOnly.resizeFactor, settingsReadOnly.rotation)
        imageThread.start()
    }

    private fun requireState(vararg requireStates: State) {
        state in requireStates ||
                throw IllegalStateException("BitmapCapture in state [$state] expected ${requireStates.contentToString()}") //#string_template
    }

    @Synchronized
    private fun setMatrix(newResizeFactor: Int, newRotation: Int) {
        (newResizeFactor != resizeFactor.get() || newRotation != rotation.get()) || return
        resizeFactor.set(newResizeFactor)
        rotation.set(newRotation)

        val newMatrix = Matrix() //#inference

        if (newResizeFactor > Settings.Values.RESIZE_DISABLED)
            newMatrix.postScale(newResizeFactor / 100f, newResizeFactor / 100f)

        if (newRotation != Settings.Values.ROTATION_0)
            newMatrix.postRotate(newRotation.toFloat())

        matrix.set(newMatrix)
        if (state == State.STARTED) restart()
    }

    @Synchronized
    fun start() {
        XLog.d(getLog("start"))
        requireState(State.INIT)

        startDisplayCapture()

        if (state == State.STARTED)
            coroutineScope.launch { //#coroutine,lambda
                val rotation = display.rotation //#inference
                var previous = rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180 //#inference
                var current: Boolean

                XLog.d(this@BitmapCapture.getLog("Start", "Rotation detector started"))
                while (isActive) {
                    delay(250)
                    val newRotation = display.rotation //#inference
                    current = newRotation == Surface.ROTATION_0 || newRotation == Surface.ROTATION_180
                    if (previous != current) {
                        XLog.d(this@BitmapCapture.getLog("Start", "Rotation detected"))
                        previous = current
                        if (state == State.STARTED) restart()
                    }
                }
            }
    }

    @Synchronized
    fun destroy() {
        XLog.d(getLog("destroy"))
        if (state == State.DESTROYED) {
            XLog.w(getLog("destroy", "Already destroyed"))
            return
        }
        requireState(State.STARTED, State.ERROR)
        coroutineScope.cancel(CancellationException("BitmapCapture.destroy"))
        state = State.DESTROYED
        settingsReadOnly.unregisterChangeListener(settingsListener)
        stopDisplayCapture()
        imageThread.quit()
    }

    @SuppressLint("WrongConstant")
    private fun startDisplayCapture() {
        val screenSize = Point().also { display.getRealSize(it) } //#inference,lambda
        imageListener = ImageListener(settingsReadOnly, resizeFactor, matrix)

        val screenSizeX: Int
        val screenSizeY: Int
        if (resizeFactor.get() < Settings.Values.RESIZE_DISABLED) {
            val scale = resizeFactor.get() / 100f //#inference
            screenSizeX = (screenSize.x * scale).toInt()
            screenSizeY = (screenSize.y * scale).toInt()
        } else {
            screenSizeX = screenSize.x
            screenSizeY = screenSize.y
        }

        imageReader = ImageReader.newInstance(screenSizeX, screenSizeY, PixelFormat.RGBA_8888, 2) 
            .apply { setOnImageAvailableListener(imageListener, imageThreadHandler) } //#lambda

        try {
            val densityDpi = DisplayMetrics().also { display.getMetrics(it) }.densityDpi //#inference,lambda
            virtualDisplay = mediaProjection.createVirtualDisplay(
                "ScreenStreamVirtualDisplay", screenSizeX, screenSizeY, densityDpi,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_PRESENTATION, imageReader?.surface, null, imageThreadHandler //#safe_call
            )
            state = State.STARTED
        } catch (ex: SecurityException) {
            state = State.ERROR
            XLog.w(getLog("startDisplayCapture", ex.toString()))
            onError(FixableError.CastSecurityException)
        }
    }

    private fun stopDisplayCapture() {
        virtualDisplay?.release() //#safe_call
        imageReader?.close() //#safe_call
    }

    @Synchronized
    private fun restart() {
        XLog.d(getLog("restart", "Start"))
        if (state != State.STARTED) {
            XLog.d(getLog("restart", "Ignored"))
        } else {
            stopDisplayCapture()
            startDisplayCapture()
            XLog.d(getLog("restart", "End"))
        }
    }

    // Runs on imageThread
    private inner class ImageListener(
        private val settingsReadOnly: SettingsReadOnly,
        private val resizeFactor: AtomicInteger,
        private val matrix: AtomicReference<Matrix>
    ) : ImageReader.OnImageAvailableListener {

        override fun onImageAvailable(reader: ImageReader) {
            synchronized(this@BitmapCapture) { //#lambda
                if (state != State.STARTED || this != imageListener) return

                try {
                    reader.acquireLatestImage()?.let { image -> //#safe_call,lambda
                        val now = System.currentTimeMillis() //#inference
                        if ((now - lastImageMillis) < (1000 / settingsReadOnly.maxFPS)) {
                            image.close()
                            return
                        }
                        lastImageMillis = now

                        val cleanBitmap = getCleanBitmap(image) //#inference
                        val croppedBitmap = getCroppedBitmap(cleanBitmap) //#inference
                        val upsizedBitmap = getUpsizedAndRotadedBitmap(croppedBitmap) //#inference

                        image.close()
                        if (outBitmapChannel.isClosedForSend.not()) outBitmapChannel.offer(upsizedBitmap)
                    }
                } catch (ex: UnsupportedOperationException) {
                    XLog.w(this@BitmapCapture.getLog("outBitmapChannel", ex.toString()))
                    state = State.ERROR
                    onError(FatalError.BitmapFormatException)
                } catch (throwable: Throwable) {
                    XLog.e(this@BitmapCapture.getLog("outBitmapChannel"), throwable)
                    state = State.ERROR
                    onError(FatalError.BitmapCaptureException)
                }
            }
        }

        private var lastImageMillis: Long = 0L
        private lateinit var reusableBitmap: Bitmap

        private fun getCleanBitmap(image: Image): Bitmap {
            val plane = image.planes[0] //#inference
            val width = plane.rowStride / plane.pixelStride //#inference
            val cleanBitmap: Bitmap

            if (width > image.width) {
                if (::reusableBitmap.isInitialized.not()) {
                    reusableBitmap = Bitmap.createBitmap(width, image.height, Bitmap.Config.ARGB_8888)
                }

                reusableBitmap.copyPixelsFromBuffer(plane.buffer)
                cleanBitmap = Bitmap.createBitmap(reusableBitmap, 0, 0, image.width, image.height)
            } else {
                cleanBitmap = Bitmap.createBitmap(image.width, image.height, Bitmap.Config.ARGB_8888)
                cleanBitmap.copyPixelsFromBuffer(plane.buffer)
            }
            return cleanBitmap
        }

        private fun getCroppedBitmap(bitmap: Bitmap): Bitmap {
            if (settingsReadOnly.vrMode == Settings.Default.VR_MODE_DISABLE && settingsReadOnly.imageCrop.not())
                return bitmap

            var imageLeft: Int = 0
            var imageRight: Int = bitmap.width

            when (settingsReadOnly.vrMode) { //#when_expr
                Settings.Default.VR_MODE_LEFT -> imageRight = bitmap.width / 2
                Settings.Default.VR_MODE_RIGHT -> imageLeft = bitmap.width / 2
            }

            var imageCropLeft: Int = 0
            var imageCropRight: Int = 0
            var imageCropTop: Int = 0
            var imageCropBottom: Int = 0
            if (settingsReadOnly.imageCrop)
                when { //#when_expr
                    resizeFactor.get() < Settings.Values.RESIZE_DISABLED -> {
                        val scale = resizeFactor.get() / 100f //#inference
                        imageCropLeft = (settingsReadOnly.imageCropLeft * scale).toInt()
                        imageCropRight = (settingsReadOnly.imageCropRight * scale).toInt()
                        imageCropTop = (settingsReadOnly.imageCropTop * scale).toInt()
                        imageCropBottom = (settingsReadOnly.imageCropBottom * scale).toInt()
                    }
                    else -> {
                        imageCropLeft = settingsReadOnly.imageCropLeft
                        imageCropRight = settingsReadOnly.imageCropRight
                        imageCropTop = settingsReadOnly.imageCropTop
                        imageCropBottom = settingsReadOnly.imageCropBottom
                    }
                }

            if (imageLeft + imageRight - imageCropLeft - imageCropRight <= 0 ||
                bitmap.height - imageCropTop - imageCropBottom <= 0
            )
                return bitmap

            return try {
                Bitmap.createBitmap(
                    bitmap, imageLeft + imageCropLeft, imageCropTop,
                    imageRight - imageLeft - imageCropLeft - imageCropRight,
                    bitmap.height - imageCropTop - imageCropBottom
                )
            } catch (ex: IllegalArgumentException) {
                XLog.w(this@BitmapCapture.getLog("getCroppedBitmap", ex.toString()))
                bitmap
            }
        }

        private fun getUpsizedAndRotadedBitmap(bitmap: Bitmap): Bitmap {
            if (matrix.get().isIdentity) return bitmap
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix.get(), false)
        }
    }
}
