/*
 * This file is part of FreedomBox.
 *
 * FreedomBox is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FreedomBox is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FreedomBox. If not, see <http://www.gnu.org/licenses/>.
 */

package org.freedombox.freedombox.views.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_setup.*
import org.freedombox.freedombox.R
import org.freedombox.freedombox.components.AppComponent
import org.freedombox.freedombox.utils.network.wrapHttps
import org.freedombox.freedombox.utils.storage.getConfiguredBoxesMap
import org.freedombox.freedombox.utils.storage.getSharedPreference
import org.freedombox.freedombox.utils.storage.putSharedPreference
import org.freedombox.freedombox.utils.view.getEnteredText
import org.freedombox.freedombox.utils.view.getSwitchStatus
import org.freedombox.freedombox.views.activities.DiscoveryActivity
import org.freedombox.freedombox.views.model.ConfigModel
import javax.inject.Inject

class SetupFragment : BaseFragment() {

    @Inject lateinit var sharedPreferences: SharedPreferences

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val currentBox = activity!!.intent.getParcelableExtra<ConfigModel>(getString(R.string.current_box)) //#inference,unsafe_call

        currentBox?.let { //#safe_call,lambda
            boxName.setText(it.boxName)
            discoveredUrl.setText(it.domain)
            defaultStatus.isChecked = it.isDefault()
        }

        saveConfig.setOnClickListener { //#lambda
            storeFreedomBoxConfiguration()
            activity!!.finish() //#unsafe_call
        }

        val configuredBoxesJSON = getSharedPreference(sharedPreferences, //#inference
                getString(R.string.saved_boxes))

        val boxes = getConfiguredBoxesMap(configuredBoxesJSON) //#inference

        // Show delete button only if the box has already been saved
        if (boxes != null && currentBox != null) {
            if (boxes.containsKey(currentBox.boxName)) {
                deleteConfig.visibility = View.VISIBLE
            }
        }

        deleteConfig.setOnClickListener{ //#lambda
            deleteFreedomBoxConfiguration()
            startActivity(Intent(activity, DiscoveryActivity::class.java))
        }
    }

    private fun deleteFreedomBoxConfiguration() {
        val configuredBoxesJSON = getSharedPreference(sharedPreferences, //#inference
                getString(R.string.saved_boxes))

        val boxes = getConfiguredBoxesMap(configuredBoxesJSON) //#inference
        boxes?.let { //#safe_call,lambda
            putSharedPreference(sharedPreferences, getString(R.string.saved_boxes),
                    it.minus(getEnteredText(boxName)))
        }
    }

    private fun storeFreedomBoxConfiguration() {
        val configuredBoxesJSON = getSharedPreference(sharedPreferences, //#inference
            getString(R.string.saved_boxes))

        val savedBoxes = getConfiguredBoxesMap(configuredBoxesJSON) ?: mapOf() //#inference

        val updatedBoxes = if(savedBoxes.isNotEmpty() && getSwitchStatus(defaultStatus)) { //#inference
            val previousDefault = savedBoxes.filterValues { it.isDefault() }.entries.firstOrNull() //#inference,lambda
            previousDefault?.let { //#safe_call,lambda
                savedBoxes.plus(Pair(previousDefault.key, previousDefault.value.copy(default = false))) //#func_call_with_named_arg
            } ?: savedBoxes
        } else savedBoxes

        val configModel = ConfigModel( //#inference
                getEnteredText(boxName),
                wrapHttps(getEnteredText(discoveredUrl)),
                // Make the first configured FreedomBox the default by default
                getSwitchStatus(defaultStatus) or savedBoxes.isEmpty())

        val configuredBoxMap = updatedBoxes.plus(Pair(configModel.boxName, configModel)) //#inference

        putSharedPreference(sharedPreferences,
            getString(R.string.saved_boxes),
            configuredBoxMap)
    }

    override fun getLayoutId() = R.layout.fragment_setup

    companion object { //#companion
        fun new(args: Bundle): SetupFragment {
            val fragment = SetupFragment() //#inference
            fragment.arguments = args

            return fragment
        }
    }

    override fun injectFragment(appComponent: AppComponent) = appComponent.inject(this)

}
