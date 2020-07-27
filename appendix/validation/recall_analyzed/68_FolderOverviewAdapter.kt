package de.ph1b.audiobook.features.folderOverview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class FolderOverviewAdapter(private val deleteClicked: (toDelete: FolderModel) -> Unit) :
  RecyclerView.Adapter<FolderOverviewHolder>() {

  private val items = ArrayList<FolderModel>() //#inference

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderOverviewHolder {
    return FolderOverviewHolder(parent) { //#lambda
      deleteClicked(items[it])
    }
  }

  override fun onBindViewHolder(holder: FolderOverviewHolder, position: Int) {
    val model = items[position] //#inference
    holder.bind(model)
  }

  fun newItems(newItems: Collection<FolderModel>) {
    val newItemsSorted = newItems.sorted() //#inference
    val hadItems = this.items.isNotEmpty() //#inference
    val diff = FolderOverviewDiffHelper.diff(this.items, newItemsSorted) //#inference
    this.items.clear()
    this.items.addAll(newItemsSorted)
    if (hadItems) diff.dispatchUpdatesTo(this)
    else notifyDataSetChanged()
  }

  override fun getItemCount(): Int = items.size
}
