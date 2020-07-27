package org.ligi.gobandroid_hd.ui.gnugo

import android.content.Context
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.setup_gnugo.view.*
import org.ligi.gobandroid_hd.R
import org.ligi.gobandroid_hd.ui.GobandroidDialog
import org.ligi.kaxt.doOnProgressChanged

class GnuGoSetupDialog(context: Context) : GobandroidDialog(context) {

    private val shared_prefs by lazy { PreferenceManager.getDefaultSharedPreferences(context) } //#inference,property_delegation,lambda

    init {

        setTitle(R.string.gnugo)
        setIconResource(R.drawable.ic_action_settings)

        setContentView(R.layout.setup_gnugo)

        if (shared_prefs.getBoolean(SP_KEY_PLAYS_BOTH, false)) {
            container.gnugo_plays_both_radio.isChecked = true
        } else if (shared_prefs.getBoolean(SP_KEY_PLAYS_WHITE, false)) {
            container.gnugo_plays_white_radio.isChecked = true
        } else if (shared_prefs.getBoolean(SP_KEY_PLAYS_BLACK, false)) {
            container.gnugo_plays_black_radio.isChecked = true
        } else {
            // no former selection - default to black
            container.gnugo_plays_black_radio.isChecked = true
        }

        var level = shared_prefs.getInt(SP_KEY_STRENGTH, 0) //#inference

        if (level > container.gnugo_strength_seek.max) {
            level = container.gnugo_strength_seek.max
        }

        container.gnugo_strength_seek.progress = level

        container.gnugo_strength.text = getContext().getString(R.string.gnugo_strength) + " " + level.toString()

        container.gnugo_strength_seek.doOnProgressChanged { progress, fromUser -> //#lambda
            if (fromUser) {
                container.gnugo_strength.text = getContext().getString(R.string.gnugo_strength) + progress.toString()
            }
        }
    }

    fun isWhiteActive() = container.gnugo_plays_white_radio.isChecked
    fun isBlackActive() = container.gnugo_plays_black_radio.isChecked
    fun isBothActive() = container.gnugo_plays_both_radio.isChecked
    fun strength() = container.gnugo_strength_seek.progress

    fun saveRecentAsDefault() {
        val edit = shared_prefs.edit() //#inference
        edit.putInt(SP_KEY_STRENGTH, strength())

        edit.putBoolean(SP_KEY_PLAYS_WHITE, isWhiteActive())
        edit.putBoolean(SP_KEY_PLAYS_BLACK, isBlackActive())
        edit.putBoolean(SP_KEY_PLAYS_BOTH, isBothActive())

        edit.apply()
    }

    companion object { //#companion

        private val SP_KEY_PLAYS_BLACK = "plays_black" //#inference
        private val SP_KEY_PLAYS_WHITE = "plays_white" //#inference
        private val SP_KEY_PLAYS_BOTH = "plays_both" //#inference

        private val SP_KEY_STRENGTH = "strength" //#inference
    }

}
