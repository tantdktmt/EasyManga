package com.easymanga.ui.episodedetail

import androidx.lifecycle.MutableLiveData
import com.easymanga.data.Page
import com.easymanga.data.network.NetworkDataManager
import com.easymanga.ui.base.BaseViewModel
import com.easymanga.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class EpisodeDetailViewModel @Inject constructor(
    networkDataManager: NetworkDataManager,
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider
) : BaseViewModel(networkDataManager, compositeDisposable, schedulerProvider) {

    val pageListLive = MutableLiveData<List<Page>>()

    fun fetchPageList(episodeUrl: String) {
        dataLoading.value = true
        compositeDisposable.add(
            networkDataManager.getPageList(episodeUrl).subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    pageListLive.value = it
                    dataLoading.value = false
                }, {
                    dataLoading.value = false
                })
        )
    }
}