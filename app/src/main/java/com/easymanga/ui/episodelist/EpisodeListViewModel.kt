package com.easymanga.ui.episodelist

import androidx.lifecycle.MutableLiveData
import com.easymanga.data.Channel
import com.easymanga.data.Episode
import com.easymanga.data.Manga
import com.easymanga.data.network.NetworkDataManager
import com.easymanga.ui.base.BaseViewModel
import com.easymanga.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class EpisodeListViewModel @Inject constructor(
    networkDataManager: NetworkDataManager,
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider
) : BaseViewModel(networkDataManager, compositeDisposable, schedulerProvider) {

    val episodeListLive = MutableLiveData<List<Episode>>()

    fun fetchEpisodeList() {
        dataLoading.value = true
        compositeDisposable.add(networkDataManager.getEpisodeList().subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                episodeListLive.value = it
                dataLoading.value = false
            }, {
                dataLoading.value = false
            }))
    }
}