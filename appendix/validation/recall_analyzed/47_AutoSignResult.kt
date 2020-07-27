package me.ykrank.s1next.data.api.model

import android.text.TextUtils
import java.util.regex.Pattern

/**
 * Created by ykrank on 2017/6/4.
 */

class AutoSignResult {
    var success: Boolean = false
    var msg: String? = null
    var signed: Boolean = false

    companion object { //#companion
        fun fromHtml(html: String): AutoSignResult {
            val result = AutoSignResult() //#inference
            if (TextUtils.isEmpty(html)) {
                result.success = false
                result.msg = "返回值为空！"
                return result
            }
            if (html.contains("succeedhandle_")) {
                result.success = true
                result.signed = true
                val pattern = Pattern.compile("succeedhandle_\\('forum.php'.+'(.+)'") //#inference
                val matcher = pattern.matcher(html) //#inference
                if (matcher.find()) {
                    result.msg = matcher.group(1)
                } else {
                    result.msg = "签到成功!"
                }
            } else {
                result.success = false
                val pattern = Pattern.compile("errorhandle_\\('(.+)'") //#inference
                val matcher = pattern.matcher(html) //#inference
                if (matcher.find()) {
                    result.msg = matcher.group(1)
                } else {
                    result.msg = html
                }
                result.signed = result.msg?.contains("已签到") ?: false //#safe_call
            }
            return result
        }
    }
}