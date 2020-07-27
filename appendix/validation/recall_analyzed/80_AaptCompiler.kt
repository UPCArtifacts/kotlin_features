package com.jereksel.libresubstratumlib.assetmanager

import android.content.res.AssetManager
import com.jereksel.libresubstratumlib.compilercommon.AndroidManifestGenerator
import com.jereksel.libresubstratumlib.compilercommon.InvalidInvocationException
import com.jereksel.libresubstratumlib.compilercommon.ThemeToCompile
import org.slf4j.LoggerFactory
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

class AaptCompiler( //#func_with_default_value
        private val aaptPath: String,
        testing: Boolean = false
) {

    private val logger = LoggerFactory.getLogger(javaClass.name) //#inference

    private val generator = AndroidManifestGenerator(testing) //#inference

    fun compileTheme(assetManager: AssetManager, themeDate: ThemeToCompile, tempDir: File,
                     additionalApks: List<String> = listOf(), transform: (InputStream) -> InputStream = { it }): File {
                     //#func_with_default_value,lambda

        val apkLocation = File(tempDir, "overlay.apk") //#inference
        val compilationDir = File(tempDir, "compilation") //#inference
        compilationDir.mkdirs()

        val manifest = generator.generateManifest(themeDate) //#inference
        val manifestFile = File(tempDir, "AndroidManifest.xml") //#inference

        manifestFile.createNewFile()
        manifestFile.writeText(manifest)

        val command = mutableListOf(aaptPath, "package", "--auto-add-overlay", "-f", "-M", manifestFile.absolutePath, "-F", apkLocation.absolutePath) //#inference

        val amLoc = "overlays/${themeDate.originalTargetApp}" //#inference,string_template

        val list = assetManager.list(amLoc).toSet() //#inference

        val mainRes = File(compilationDir, "res") //#inference

        val type3CommonLocation = "$amLoc/type3-common" //#inference,string_template

        val type3 = themeDate.type3 //#inference

        if (type3 != null && !type3.default) {
            val amLocation = "$amLoc/type3_${type3.name}" //#inference,string_template

            if (assetManager.list(type3CommonLocation).isNotEmpty()) {
                if (assetManager.list(type3CommonLocation).contains("res")) {
                    assetManager.extract("$type3CommonLocation/res", mainRes, transform) //#string_template
                } else {
                    assetManager.extract(type3CommonLocation, mainRes, transform)
                }
            }

            if (assetManager.list(amLocation).contains("res")) {
                //Some themes have "res/" in type3
                assetManager.extract("$amLocation/res", mainRes, transform) //#string_template
            } else {
                assetManager.extract(amLocation, mainRes, transform)
            }

        } else {
            if (list.contains("res")) {
                mainRes.mkdirs()
                assetManager.extract("$amLoc/res", mainRes, transform) //#string_template
            }
        }

        themeDate.type1
                .filterNot { it.extension.default } //#lambda
                .forEach { //#lambda
                    val amLocation = "$amLoc/type1${it.suffix}_${it.extension.name}.xml" //#inference,string_template
                    val amLocationEnc = "$amLoc/type1${it.suffix}_${it.extension.name}.xml.enc" //#inference,string_template
                    val source = File(tempDir, "type1${it.suffix}_${it.extension.name}.xml") //#inference,string_template
                    assetManager.extract(amLocation, source, transform)
                    assetManager.extract(amLocationEnc, source, transform)
                    val dest = File(mainRes, "values", "type1${it.suffix}.xml") //#inference,string_template
                    source.copyTo(dest, overwrite = true) //#func_call_with_named_arg
                }

        additionalApks.forEach { //#lambda
            command.addAll(listOf("-I", it))
        }

        val type2 = themeDate.type2 //#inference

        if (type2 != null && !type2.default) {
            val file = File(tempDir, "type2") //#inference
            val amLocation = "$amLoc/type2_${type2.name}" //#inference,string_template
            assetManager.extract(amLocation, file, transform)
            if (File(file, "res").exists()) {
                command.addAll(listOf("-S", File(file, "res").absolutePath))
            } else if (file.exists()) {
                command.addAll(listOf("-S", file.absolutePath))
            }
        }

        if (mainRes.exists() && mainRes.list().isNotEmpty()) {
            command.addAll(listOf("-S", mainRes.absolutePath))
        }

        logger.debug("Invoking: {}", command.joinToString(separator = " ")) //#func_call_with_named_arg

        logger.debug("AndroidManifest:\n{}", manifest)

        val proc = ProcessBuilder(command) //#inference
                .start()

        val statusCode = proc.waitFor() //#inference

        val output = proc.inputStream.bufferedReader().use { it.readText() } //#inference,lambda
        val error = proc.errorStream.bufferedReader().use { it.readText() } //#inference,lambda

        if (statusCode != 0) {
            throw InvalidInvocationException(error)
        }

        return apkLocation
    }

    private fun AssetManager.extract(location: String, dest: File, transform: (InputStream) -> (InputStream)) { //#extension_function

        val children = this.list(location) //#inference

        if (children.isEmpty()) {
            //This is file
            dest.parentFile.mkdirs()

            val `is`: InputStream

            try {
                `is` = open(location)
            } catch (e: FileNotFoundException) {
                return
            }

            if (dest.exists()) {
                dest.delete()
            }

            dest.createNewFile()

            `is`.use { inputStream -> //#lambda
                dest.outputStream().use { fileOutputStream -> //#lambda
                    transform(inputStream).copyTo(fileOutputStream)
                }
            }

            if (dest.name.endsWith(".enc")) {
                val newFile = File(dest.absolutePath.removeSuffix(".enc")) //#inference
                if (newFile.exists()) {
                    newFile.delete()
                }
                dest.renameTo(newFile)
            }

        } else {
            children.forEach { extract("$location/$it", File(dest, it), transform) } //#lambda,string_template
        }

    }

    fun File(file: File, vararg subDirs: String) = subDirs.fold(file) { total, next -> java.io.File(total, next) } //#lambda

    private fun AssetManager.fileExists(location: String) = //#extension_function
            try {
                this.open(location).use {  } //#lambda
                true
            } catch (e: FileNotFoundException) {
                false
            }

}
