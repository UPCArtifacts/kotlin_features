package org.mozilla.rocket.shopping.search.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import dagger.Lazy
import org.mozilla.focus.R
import org.mozilla.focus.activity.BaseActivity
import org.mozilla.focus.telemetry.TelemetryWrapper
import org.mozilla.focus.widget.BackKeyHandleable
import org.mozilla.rocket.content.appComponent
import org.mozilla.rocket.content.common.ui.TabSwipeTelemetryViewModel
import org.mozilla.rocket.content.getViewModel
import org.mozilla.rocket.privately.PrivateTabViewProvider
import org.mozilla.rocket.tabs.SessionManager
import org.mozilla.rocket.tabs.TabViewProvider
import org.mozilla.rocket.tabs.TabsSessionProvider
import javax.inject.Inject

class ShoppingSearchActivity : BaseActivity(), TabsSessionProvider.SessionHost {

    @Inject
    lateinit var telemetryViewModelCreator: Lazy<TabSwipeTelemetryViewModel>

    private lateinit var telemetryViewModel: TabSwipeTelemetryViewModel

    private var sessionManager: SessionManager? = null
    private lateinit var tabViewProvider: TabViewProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_search)
        telemetryViewModel = getViewModel(telemetryViewModelCreator)
        tabViewProvider = PrivateTabViewProvider(this)
        makeStatusBarTransparent()
    }

    override fun onResume() {
        super.onResume()
        telemetryViewModel.onProcessSessionStarted(TelemetryWrapper.Extra_Value.SHOPPING)
    }

    override fun onPause() {
        super.onPause()
        telemetryViewModel.onProcessSessionEnded()
    }

    override fun onDestroy() {
        super.onDestroy()
        sessionManager?.destroy() //#safe_call
    }

    override fun onBackPressed() {
        if (supportFragmentManager.isStateSaved) {
            return
        }

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) //#inference
        navHost?.let { navFragment -> //#safe_call,lambda
            navFragment.childFragmentManager.primaryNavigationFragment?.let { fragment -> //#safe_call,lambda
                if (fragment is BackKeyHandleable) { //#smart_cast
                    val handled = fragment.onBackPressed() //#inference
                    if (handled) {
                        return
                    }
                }
            }
        }

        super.onBackPressed()
    }

    override fun applyLocale() = Unit

    override fun getSessionManager(): SessionManager {
        if (sessionManager == null) {
            sessionManager = SessionManager(tabViewProvider)
        }

        // we just created it, it definitely not null
        return sessionManager!! //#unsafe_call
    }

    private fun makeStatusBarTransparent() {
        var visibility = window.decorView.systemUiVisibility //#inference
        // do not overwrite existing value
        visibility = visibility or (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.decorView.systemUiVisibility = visibility
    }

    companion object { //#companion
        fun getStartIntent(context: Context) =
            Intent(context, ShoppingSearchActivity::class.java).also { it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) } //#lambda
    }
}
