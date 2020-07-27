package pl.hypeapp.episodie.ui.viewmodel.seasontracker

import pl.hypeapp.domain.model.WatchState
import pl.hypeapp.episodie.ui.base.adapter.ViewType

data class RemainingTimeViewModel(val runtime: Long?, //#data_class
                                  val watchState: String = WatchState.NOT_WATCHED) : ViewType { //#func_with_default_value

    override fun getViewType(): Int = ViewType.SeasonTrackerViewType.TIME_REMAINING_ITEM

}
