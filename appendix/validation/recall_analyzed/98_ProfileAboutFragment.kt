package me.proxer.app.profile.about

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import androidx.core.content.getSystemService
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.lifecycle.Lifecycle
import com.gojuno.koptional.rxjava2.filterSome
import com.gojuno.koptional.toOptional
import com.uber.autodispose.android.lifecycle.scope
import com.uber.autodispose.autoDisposable
import kotterknife.bindView
import me.proxer.app.R
import me.proxer.app.base.BaseContentFragment
import me.proxer.app.profile.ProfileActivity
import me.proxer.app.ui.view.ProxerWebView
import me.proxer.app.util.ErrorUtils.ErrorAction
import me.proxer.app.util.ErrorUtils.ErrorAction.Companion.ACTION_MESSAGE_HIDE
import me.proxer.app.util.extension.linkClicks
import me.proxer.app.util.extension.linkLongClicks
import me.proxer.app.util.extension.linkify
import me.proxer.app.util.extension.toAppString
import me.proxer.app.util.extension.toPrefixedUrlOrNull
import me.proxer.app.util.extension.toast
import me.proxer.library.entity.user.UserAbout
import me.proxer.library.enums.Gender
import me.proxer.library.enums.RelationshipStatus
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * @author Ruben Gees
 */
class ProfileAboutFragment : BaseContentFragment<UserAbout>(R.layout.fragment_about) {

    companion object { //#companion
        private const val ZERO_DATE = "0000-00-00" //#inference

        fun newInstance() = ProfileAboutFragment().apply { //#lambda
            arguments = bundleOf()
        }
    }

    override val hostingActivity: ProfileActivity
        get() = activity as ProfileActivity

    override val viewModel by viewModel<ProfileAboutViewModel> { //#inference,property_delegation,lambda
        parametersOf(userId, username)
    }

    private val userId: String?
        get() = hostingActivity.userId

    private val username: String?
        get() = hostingActivity.username

    private val generalContainer by bindView<ViewGroup>(R.id.generalContainer) //#inference,property_delegation
    private val generalTable by bindView<TableLayout>(R.id.generalTable) //#inference,property_delegation
    private val aboutContainer by bindView<ViewGroup>(R.id.aboutContainer) //#inference,property_delegation
    private val about by bindView<ProxerWebView>(R.id.about) //#inference,property_delegation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        about.showPageSubject
            .autoDisposable(viewLifecycleOwner.scope())
            .subscribe { showPage(it) } //#lambda
    }

    override fun onDestroyView() {
        about.destroy()

        super.onDestroyView()
    }

    override fun showData(data: UserAbout) {
        super.showData(data)

        addTableRows(data)

        data.about.let { //#lambda
            when (it.isNotBlank()) { //#when_expr
                true -> about.loadHtml(it)
                false -> aboutContainer.isGone = true
            }
        }

        if (generalTable.childCount <= 0) {
            generalContainer.isGone = true
        }

        if (generalContainer.isGone && aboutContainer.isGone) {
            showError(ErrorAction(R.string.error_no_data_profile_about, ACTION_MESSAGE_HIDE))
        }
    }

    private fun addTableRows(data: UserAbout) {
        val normalizedGender = when (data.gender) { //#inference,when_expr
            Gender.UNKNOWN -> ""
            else -> data.gender.toAppString(requireContext())
        }

        val normalizedRelationshipStatus = when (data.relationshipStatus) { //#inference,when_expr
            RelationshipStatus.UNKNOWN -> ""
            else -> data.relationshipStatus.toAppString(requireContext())
        }

        val normalizedBirthday = when (data.birthday) { //#inference,when_expr
            ZERO_DATE -> ""
            else -> data.birthday.split("-").let { //#lambda
                when (it.size) { //#when_expr
                    3 -> {
                        val (year, month, day) = it //#inference,inference,inference,destruct_declaration

                        "$day.$month.$year"//#string_template
                    }
                    else -> ""
                }
            }
        }

        addTableRowIfNotBlank(getString(R.string.fragment_about_occupation), data.occupation)
        addTableRowIfNotBlank(getString(R.string.fragment_about_interests), data.interests)
        addTableRowIfNotBlank(getString(R.string.fragment_about_city), data.city)
        addTableRowIfNotBlank(getString(R.string.fragment_about_country), data.country)
        addTableRowIfNotBlank(getString(R.string.fragment_about_gender), normalizedGender)
        addTableRowIfNotBlank(getString(R.string.fragment_about_relationship_status), normalizedRelationshipStatus)
        addTableRowIfNotBlank(getString(R.string.fragment_about_birthday), normalizedBirthday)
        addTableRowIfNotBlank(getString(R.string.fragment_about_website), data.website)
        addTableRowIfNotBlank(getString(R.string.fragment_about_facebook), data.facebook)
        addTableRowIfNotBlank(getString(R.string.fragment_about_youtube), data.youtube)
        addTableRowIfNotBlank(getString(R.string.fragment_about_chatango), data.chatango)
        addTableRowIfNotBlank(getString(R.string.fragment_about_twitter), data.twitter)
        addTableRowIfNotBlank(getString(R.string.fragment_about_skype), data.skype)
        addTableRowIfNotBlank(getString(R.string.fragment_about_deviantart), data.deviantart)
    }

    private fun addTableRowIfNotBlank(title: String, content: String) {
        val view = if (content.isNotBlank()) constructTableRow(title, content) else null //#inference

        if (view != null) generalTable.addView(view)
    }

    private fun constructTableRow(title: String, content: String): View {
        val tableRow = LayoutInflater.from(context).inflate(R.layout.layout_about_row, generalTable, false) //#inference
        val titleView = tableRow.findViewById<TextView>(R.id.title) //#inference
        val contentView = tableRow.findViewById<TextView>(R.id.content) //#inference

        contentView.setTextIsSelectable(true)
        contentView.isSaveEnabled = false

        titleView.text = title
        contentView.text = content.linkify(mentions = false) //#func_call_with_named_arg

        contentView.linkClicks()
            .map { it.toPrefixedUrlOrNull().toOptional() } //#lambda
            .filterSome()
            .autoDisposable(viewLifecycleOwner.scope(Lifecycle.Event.ON_DESTROY))
            .subscribe { showPage(it) } //#lambda

        contentView.linkLongClicks()
            .autoDisposable(viewLifecycleOwner.scope())
            .subscribe { //#lambda
                val clipboardTitle = getString(R.string.clipboard_title) //#inference

                requireContext().getSystemService<ClipboardManager>()?.setPrimaryClip( //#safe_call
                    ClipData.newPlainText(clipboardTitle, it.toString())
                )

                requireContext().toast(R.string.clipboard_status)
            }

        return tableRow
    }
}
