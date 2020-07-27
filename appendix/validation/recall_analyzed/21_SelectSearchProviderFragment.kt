package ch.deletescape.lawnchair.globalsearch.ui

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.preference.PreferenceDialogFragmentCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import ch.deletescape.lawnchair.applyAccent
import ch.deletescape.lawnchair.colors.ColorEngine
import ch.deletescape.lawnchair.globalsearch.SearchProviderController
import ch.deletescape.lawnchair.globalsearch.SearchProvider
import com.android.launcher3.R
import com.android.launcher3.Utilities

class SelectSearchProviderFragment : PreferenceDialogFragmentCompat() {

    private val key by lazy { arguments!!.getString("key") } //#inference,property_delegation,lambda,unsafe_call
    private val value by lazy { arguments!!.getString("value") }  //#inference,property_delegation,lambda,unsafe_call

    private var selectedProvider: SearchProvider? = null

    override fun onBindDialogView(view: View) {
        super.onBindDialogView(view)

        val recyclerView = view.findViewById<RecyclerView>(R.id.list) //#inference
        recyclerView.adapter = ProviderListAdapter(activity as Context)
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun saveChanges() {
        Utilities.getLawnchairPrefs(activity).sharedPrefs.edit().putString(key, selectedProvider.toString()).apply()
        dismiss()
    }

    override fun onPrepareDialogBuilder(builder: AlertDialog.Builder) {
        super.onPrepareDialogBuilder(builder)

        builder.setPositiveButton(null, null)
    }

    override fun onStart() {
        super.onStart()
        (dialog as AlertDialog).applyAccent()
    }

    override fun onDialogClosed(positiveResult: Boolean) {

    }

    inner class ProviderListAdapter(private val context: Context) : RecyclerView.Adapter<ProviderListAdapter.Holder>() {

        val Providers = SearchProviderController.getSearchProviders(context) //#inference

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            return Holder(LayoutInflater.from(context).inflate(R.layout.gesture_item, parent, false))
        }

        override fun getItemCount() = Providers.size

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.text.text = Providers[position].name
            holder.text.isChecked = Providers[position]::class.java.name == value
        }

        inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

            val text = itemView.findViewById<CheckedTextView>(android.R.id.text1)!!.apply { //#inference,unsafe_call,lambda
                setOnClickListener(this@Holder)
                val tintList = ColorStateList.valueOf(ColorEngine.getInstance(context).accent) //#inference
                if (Utilities.ATLEAST_MARSHMALLOW) {
                    compoundDrawableTintList = tintList
                }
                backgroundTintList = tintList
            }

            override fun onClick(v: View) {
                selectedProvider = Providers[adapterPosition]
                saveChanges()
            }
        }
    }

    companion object { //#companion

        fun newInstance(preference: SearchProviderPreference) = SelectSearchProviderFragment().apply { //#lambda
            arguments = Bundle(2).apply { //#lambda
                putString("key", preference.key)
                putString("value", preference.value)
            }
        }
    }
}