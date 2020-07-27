package org.walleth.securityinfo


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.scottyab.rootbeer.RootBeer
import kotlinx.android.synthetic.main.activity_security_info.*
import kotlinx.android.synthetic.main.activity_security_item.view.*
import org.ligi.compat.HtmlCompat
import org.walleth.R
import org.walleth.base_activities.BaseSubActivity
import org.walleth.securityinfo.ProblemLevel.*
import org.walleth.util.security.isDeviceLockScreenProtected

class SecurityInfoActivity : BaseSubActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_security_info)

        supportActionBar?.run { //#safe_call,lambda
            subtitle = getString(R.string.security_info)
        }
    }

    override fun onResume() {
        super.onResume()

        val inflater = LayoutInflater.from(this) //#inference

        val infoList = listOf( //#inference
                getPatchInfo(),
                getRootInfo(),
                getLockInfo(),
                getNoAuditWarning()
        )

        security_info_content.removeAllViews()

        infoList.forEach { //#lambda
            val view = inflater.inflate(R.layout.activity_security_item, security_info_content, false) //#inference

            view.security_info_text.text = HtmlCompat.fromHtml(it.message)
            view.security_info_icon.setImageResource(when (it.level) { //#when_expr
                GREEN -> R.drawable.ic_check_box_black_24dp
                ORANGE -> R.drawable.ic_warning_orange_24dp
                RED -> R.drawable.ic_warning_red_24dp
            })
            security_info_content.addView(view)
        }

    }

}

private fun Context.getNoAuditWarning() = SecurityInfoItem(ORANGE, getString(R.string.security_info_no_audit_warning)) //#extension_function

private fun Context.getLockInfo() = if (isDeviceLockScreenProtected(this)) { //#extension_function
    SecurityInfoItem(GREEN, getString(R.string.security_info_has_lockscreen))
} else {
    SecurityInfoItem(RED, getString(R.string.security_info_no_lockscreen))
}

private fun Context.getPatchInfo() = getDaysSincePatch().let { //#extension_function,lambda
    when { //#when_expr

        it == null -> SecurityInfoItem(RED, getString(R.string.security_info_patch_date_unknown))

        it < 33 -> SecurityInfoItem(GREEN, getString(R.string.security_info_pached_bad, it))

        it < 123 -> SecurityInfoItem(ORANGE, getString(R.string.security_info_patched_reasonably, it))

        else -> SecurityInfoItem(RED, getString(R.string.security_info_patched_badly, it)
        )
    }
}

private fun Context.getRootInfo() = if (RootBeer(this).isRooted) { //#extension_function
    SecurityInfoItem(RED, getString(R.string.security_info_rooted))
} else {
    SecurityInfoItem(GREEN, getString(R.string.security_info_not_rooted))
}
