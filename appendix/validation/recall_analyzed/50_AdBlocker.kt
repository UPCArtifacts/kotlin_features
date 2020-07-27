/*
 * Copyright 2018 Allan Wang
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.pitchedapps.frost.utils

import android.content.Context
import android.text.TextUtils
import ca.allanwang.kau.utils.use
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.HttpUrl

/**
 * Created by Allan Wang on 2017-09-24.
 */
object FrostAdBlock : AdBlocker("adblock.txt") //#singleton

object FrostPglAdBlock : AdBlocker("pgl.yoyo.org.txt") //#singleton

/**
 * Base implementation of an AdBlocker
 * Wrap this in a singleton and initialize it to use it
 */
open class AdBlocker(val assetPath: String) {

    val data: MutableSet<String> = mutableSetOf()

    fun init(context: Context) {
        GlobalScope.launch { //#coroutine,lambda
            val content = context.assets.open(assetPath).bufferedReader().use { f -> //#inference,lambda,lambda
                f.readLines().filter { !it.startsWith("#") } //#lambda
            }
            data.addAll(content)
            L.i { "Initialized adblock for $assetPath with ${data.size} hosts" } //#lambda,string_template
        }
    }

    fun isAd(url: String?): Boolean {
        url ?: return false
        val httpUrl = HttpUrl.parse(url) ?: return false //#inference
        return isAdHost(httpUrl.host())
    }

    tailrec fun isAdHost(host: String): Boolean { //#tailrec_function
        if (TextUtils.isEmpty(host))
            return false
        val index = host.indexOf(".") //#inference
        if (index < 0 || index + 1 < host.length) return false
        if (data.contains(host)) return true
        return isAdHost(host.substring(index + 1))
    }
}
