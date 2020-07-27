/*
 * Copyright 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.apps.muzei.browse

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.api.load
import com.google.android.apps.muzei.room.Artwork
import com.google.android.apps.muzei.room.MuzeiDatabase
import com.google.android.apps.muzei.room.getCommands
import com.google.android.apps.muzei.sync.ProviderManager
import com.google.android.apps.muzei.util.toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.nurik.roman.muzei.R
import net.nurik.roman.muzei.databinding.BrowseProviderFragmentBinding
import net.nurik.roman.muzei.databinding.BrowseProviderItemBinding

class BrowseProviderFragment: Fragment(R.layout.browse_provider_fragment) {

    companion object { //#companion
        const val REFRESH_DELAY = 300L // milliseconds //#inference
    }

    private val viewModel: BrowseProviderViewModel by viewModels() //#property_delegation
    private val args: BrowseProviderFragmentArgs by navArgs()  //#property_delegation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = BrowseProviderFragmentBinding.bind(view) //#inference
        val pm = requireContext().packageManager //#inference
        val providerInfo = pm.resolveContentProvider(args.contentUri.authority!!, 0) //#inference,unsafe_call
                ?: run { //#lambda
                    findNavController().popBackStack()
                    return
                }

        binding.swipeRefresh.setOnRefreshListener { //#lambda
            refresh(binding.swipeRefresh)
        }
        binding.toolbar.apply { //#lambda
            navigationIcon = DrawerArrowDrawable(requireContext()).apply { //#lambda
                progress = 1f
            }
            setNavigationOnClickListener { //#lambda
                findNavController().popBackStack()
            }
            title = providerInfo.loadLabel(pm)
            inflateMenu(R.menu.browse_provider_fragment)
            setOnMenuItemClickListener { //#lambda
                refresh(binding.swipeRefresh)
                true
            }
        }
        val adapter = Adapter() //#inference
        binding.list.adapter = adapter

        viewModel.contentUri = args.contentUri
        viewModel.artLiveData.observe(viewLifecycleOwner) { //#lambda
            adapter.submitList(it)
        }
    }

    private fun refresh(swipeRefreshLayout: SwipeRefreshLayout) {
        lifecycleScope.launch { //#coroutine,lambda
            ProviderManager.requestLoad(requireContext(), args.contentUri)
            // Show the refresh indicator for some visible amount of time
            // rather than immediately dismissing it. We don't know how long
            // the provider will actually take to refresh, if it does at all.
            delay(REFRESH_DELAY)
            withContext(Dispatchers.Main.immediate) { //#lambda
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    class ArtViewHolder(
            private val owner: LifecycleOwner,
            private val binding: BrowseProviderItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(artwork: Artwork) {
            val context = itemView.context //#inference
            binding.image.contentDescription = artwork.title
            binding.image.load(artwork.imageUri) { //#lambda
                lifecycle(owner)
            }
            itemView.setOnClickListener { //#lambda
                owner.lifecycleScope.launch(Dispatchers.Main) { //#coroutine,lambda
                    Firebase.analytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) { //#lambda
                        param(FirebaseAnalytics.Param.ITEM_LIST_ID, artwork.providerAuthority)
                        param(FirebaseAnalytics.Param.ITEM_NAME, artwork.title ?: "")
                        param(FirebaseAnalytics.Param.ITEM_LIST_NAME, "actions")
                        param(FirebaseAnalytics.Param.CONTENT_TYPE, "browse")
                    }
                    MuzeiDatabase.getInstance(context).artworkDao()
                            .insert(artwork)
                    context.toast(if (artwork.title.isNullOrBlank()) {
                        context.getString(R.string.browse_set_wallpaper)
                    } else {
                        context.getString(R.string.browse_set_wallpaper_with_title,
                                artwork.title)
                    })
                }
            }
            itemView.setOnCreateContextMenuListener(null)
            owner.lifecycleScope.launch(Dispatchers.Main.immediate) { //#coroutine,lambda
                val actions = artwork.getCommands(context).filterNot { //#inference,lambda
                    it.title.isBlank()
                }
                if (actions.isNotEmpty()) {
                    itemView.setOnCreateContextMenuListener { menu, _, _ -> //#lambda
                        actions.forEachIndexed { index, action -> //#lambda
                            menu.add(Menu.NONE, index, index, action.title).apply { //#lambda
                                    setOnMenuItemClickListener { menuItem -> //#lambda
                                        Firebase.analytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) { //#lambda
                                            param(FirebaseAnalytics.Param.ITEM_LIST_ID, artwork.providerAuthority)
                                            param(FirebaseAnalytics.Param.ITEM_NAME, menuItem.title.toString())
                                            param(FirebaseAnalytics.Param.ITEM_LIST_NAME, "actions")
                                            param(FirebaseAnalytics.Param.CONTENT_TYPE, "browse")
                                        }
                                    action.actionIntent.send()
                                    true
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    inner class Adapter: ListAdapter<Artwork, ArtViewHolder>(
            object: DiffUtil.ItemCallback<Artwork>() { 
                override fun areItemsTheSame(artwork1: Artwork, artwork2: Artwork) =
                        artwork1.imageUri == artwork2.imageUri

                override fun areContentsTheSame(artwork1: Artwork, artwork2: Artwork) =
                        artwork1 == artwork2
            }
    ) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                ArtViewHolder(viewLifecycleOwner,
                        BrowseProviderItemBinding.inflate(layoutInflater, parent, false))

        override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
            holder.bind(getItem(position))
        }
    }
}
