package org.fossasia.badgemagic.util

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.util.SparseArray
import java.math.BigInteger
import org.fossasia.badgemagic.data.badge_preview.CheckList
import org.fossasia.badgemagic.data.device.DataToByteArrayConverter

const val DRAWABLE_START = '«'
const val DRAWABLE_END = '»'

object Converters {
    fun convertDrawableToLEDHex(drawableIcon: Drawable?, invertLED: Boolean): List<String> {
        var bm = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888)
        if (drawableIcon is VectorDrawable)
            bm = ImageUtils.scaleBitmap(ImageUtils.vectorToBitmap(drawableIcon), 40)
        else if (drawableIcon is BitmapDrawable)
            bm = ImageUtils.scaleBitmap((drawableIcon).bitmap, 40)
        return convertBitmapToLEDHex(bm, invertLED)
    }

    fun convertBitmapToLEDHex(bm: Bitmap, invertLED: Boolean): List<String> {
        val height = bm.height
        val width = bm.width

        val image = Array(height) { IntArray(width) }
        for (i in 0 until height) {
            for (j in 0 until width) {
                image[i][j] = if (bm.getPixel(j, i) != 0) 1 else 0
            }
        }
        var finalSum = 0
        for (j in 0 until width) {
            var sum = 0
            for (i in 0 until height) {
                sum += image[i][j]
            }
            if (sum == 0) {
                for (i in 0 until height) {
                    image[i][j] = -1
                }
            } else {
                finalSum += j
                break
            }
        }

        for (j in (width - 1) downTo 0) {
            var sum = 0
            for (i in 1 until height) {
                sum += image[i][j]
            }
            if (sum == 0) {
                for (i in 0 until height) {
                    image[i][j] = -1
                }
            } else {
                finalSum += (height) - j - 1
                break
            }
        }

        var diff = 0
        if ((height - finalSum) % 8 > 0)
            diff = 8 - (height - finalSum) % 8

        val rOff = Math.floor((diff.toFloat() / 2).toDouble()).toInt()
        val lOff = Math.ceil((diff.toFloat() / 2).toDouble()).toInt()

        val list: MutableList<MutableList<Int>> = mutableListOf()
        for (i in 0 until height) {
            val row = mutableListOf<Int>()
            for (j in 0 until rOff) {
                row.add(0)
            }
            list.add(row)
        }
        for (i in 0 until height) {
            for (j in 0 until width) {
                if (image[i][j] != -1)
                    list[i].add(image[i][j]
                    )
            }
        }
        for (i in 0 until height) {
            for (j in 0 until lOff) {
                list[i].add(0)
            }
        }

        // Reformatting Against invertLED
        for (i in 0 until list.size) {
            for (j in 0 until list[0].size) {
                list[i][j] = if (list[i][j] == 1) {
                    if (!invertLED) 1 else 0
                } else {
                    if (!invertLED) 0 else 1
                }
            }
        }

        val allHexs = mutableListOf<String>()
        for (i in 0 until list[0].size / 8) {
            val lineHex = StringBuilder()
            for (k in 0 until height) {

                val stBuilder = StringBuilder()
                for (j in i * 8 until i * 8 + 8) {
                    stBuilder.append(list[k][j])
                }
                val hex = StringBuilder(BigInteger(stBuilder.toString(), 2).toString(16))
                if (hex.length == 1)
                    hex.insert(0, '0')
                lineHex.append(hex.toString())
            }
            allHexs.add(lineHex.toString())
        }
        return allHexs
    }

    fun hexToBin(s: String): String {
        val number = BigInteger(s, 16).toString(2)
        val sb = StringBuilder(number)
        for (i in 0 until 8 - number.length) {
            sb.insert(0, "0")
        }
        return sb.toString()
    }

    private fun invertHex(hex: String): String {
        val stBuilder = StringBuilder()
        for (i in 0 until hex.length / 2) {
            val tempstBuilder = StringBuilder()
            val bin = hexToBin(hex.substring(i * 2, i * 2 + 2))
            for (char in bin)
                tempstBuilder.append(if (char == '0') '1' else '0')
            var newHex = BigInteger(tempstBuilder.toString(), 2).toString(16)
            for (j in 0 until 2 - newHex.length) {
                newHex = "0$newHex"
            }
            stBuilder.append(newHex)
        }
        return stBuilder.toString()
    }

    fun convertTextToLEDHex(data: String, invertLED: Boolean): Pair<Boolean, List<String>> {
        var valid = true
        val list = mutableListOf<String>()
        for (letter in data) {
            if (DataToByteArrayConverter.CHAR_CODES.containsKey(letter)) {
                list.add(
                    if (invertLED)
                        invertHex(DataToByteArrayConverter.CHAR_CODES.getValue(letter))
                    else
                        DataToByteArrayConverter.CHAR_CODES.getValue(letter)
                )
            } else {
                valid = false
            }
        }
        return Pair(valid, list)
    }

    fun fixLEDHex(allHex: List<String>, isInverted: Boolean): List<String> {
        if (!isInverted) {
            return allHex
        }
        val list = mutableListOf<String>()
        for (str in allHex) {
            list.add(invertHex(str))
        }
        return list
    }

    fun convertEditableToLEDHex(editable: String, invertLED: Boolean, drawableSparse: SparseArray<Drawable>): List<String> {
        val listOfArt = mutableListOf<String>()
        var i = 0
        while (i < editable.length) {
            val ch = editable[i]
            if (ch == DRAWABLE_START) {
                val foundIndex = editable.indexOf(DRAWABLE_END, i)
                i = if (foundIndex > 0) {
                    listOfArt.addAll(
                        handleInvertLED(convertDrawableToLEDHex(drawableSparse.get(editable.substring(i + 1, foundIndex).toInt()), invertLED), i == 0 && invertLED)
                    )
                    foundIndex + 1
                } else {
                    editable.length
                }
            } else {
                listOfArt.addAll(
                    handleInvertLED(convertTextToLEDHex(ch.toString(), invertLED).second, i == 0 && invertLED)
                )
                i++
            }
        }
        return listOfArt
    }

    private fun handleInvertLED(hexStrings: List<String>, addPrefix: Boolean): List<String> {
        if (!addPrefix)
            return hexStrings

        val listNew = mutableListOf<String>()
        if (checkValueInFirstColumn(hexStrings))
            listNew.add("0101010101010101010101")
        listNew.addAll(hexStrings)
        return listNew
    }

    private fun checkValueInFirstColumn(hexStrings: List<String>): Boolean {
        for (i in 0 until hexStrings[0].length step 2) {
            if (BigInteger(hexStrings[0][i].toString(), 16).toString(10).toInt() < 8)
                return true
        }
        return false
    }

    fun convertStringsToLEDHex(list: ArrayList<CheckList>): Bitmap {
        val newBitmap = Bitmap.createBitmap(list[0].list.size, list.size, Bitmap.Config.ARGB_8888)
        for (i in 0 until list.size) {
            for (j in 0 until list[0].list.size) {
                newBitmap.setPixel(j, i,
                    if (list[i].list[j])
                        Color.BLACK
                    else
                        Color.TRANSPARENT
                )
            }
        }
        return newBitmap
    }
}
