package com.commit451.gitlab.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.commit451.gitlab.App
import com.commit451.gitlab.R
import com.commit451.gitlab.activity.PickBranchOrTagActivity
import com.commit451.gitlab.adapter.TagAdapter
import com.commit451.gitlab.extension.with
import com.commit451.gitlab.model.Ref
import com.commit451.gitlab.model.api.Tag
import kotlinx.android.synthetic.main.fragment_pick_tag.*
import timber.log.Timber

/**
 * Pick a branch, any branch
 */
class PickTagFragment : BaseFragment() {

    companion object { //#companion

        private const val EXTRA_PROJECT_ID = "project_id" //#inference
        private const val EXTRA_CURRENT_REF = "current_ref" //#inference

        fun newInstance(projectId: Long, ref: Ref?): PickTagFragment {
            val fragment = PickTagFragment() //#inference
            val args = Bundle() //#inference
            args.putLong(EXTRA_PROJECT_ID, projectId)
            args.putParcelable(EXTRA_CURRENT_REF, ref)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var adapterTags: TagAdapter

    private var projectId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        projectId = arguments?.getLong(EXTRA_PROJECT_ID)!! //#safe_call,unsafe_call
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pick_tag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ref = arguments?.getParcelable<Ref>(EXTRA_CURRENT_REF)!! //#inference,safe_call,unsafe_call
        adapterTags = TagAdapter(ref, object : TagAdapter.Listener { //#lambda
            override fun onTagClicked(entry: Tag) {
                val data = Intent() //#inference
                val newRef = Ref(Ref.TYPE_TAG, entry.name) //#inference
                data.putExtra(PickBranchOrTagActivity.EXTRA_REF, newRef)
                activity?.setResult(Activity.RESULT_OK, data) //#safe_call
                activity?.finish() //#safe_call
            }
        })
        listProjects.layoutManager = LinearLayoutManager(activity)
        listProjects.adapter = adapterTags

        loadData()
    }

    override fun loadData() {
        if (view == null) {
            return
        }
        progress.visibility = View.VISIBLE
        textMessage.visibility = View.GONE

        App.get().gitLab.getTags(projectId)
                .with(this)
                .subscribe({ //#lambda
                    progress.visibility = View.GONE
                    adapterTags.setEntries(it)
                }, { //#lambda
                    Timber.e(it)
                    progress.visibility = View.GONE
                    textMessage.visibility = View.VISIBLE
                })
    }
}
