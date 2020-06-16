package com.easymanga.ui

import androidx.lifecycle.MutableLiveData
import com.easymanga.data.Channel
import com.easymanga.data.Episode
import com.easymanga.data.Manga
import com.easymanga.data.Page
import com.easymanga.data.network.NetworkDataManager
import com.easymanga.ui.base.BaseViewModel
import com.easymanga.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SharedViewModel @Inject constructor(
    networkDataManager: NetworkDataManager,
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider
) : BaseViewModel(networkDataManager, compositeDisposable, schedulerProvider) {

    val channelList = MutableLiveData<List<Channel>>()
    val episodeList = MutableLiveData<List<Episode>>()
    val mangaList = MutableLiveData<List<Manga>>()
    val pageList = MutableLiveData<List<Page>>()
    val mangaDetail = MutableLiveData<Manga>()

    fun fetchChannelList() {
        dataLoading.value = true
        networkDataManager.getChannels { isSuccess, channels ->
            dataLoading.value = false
            if (isSuccess) {
                channelList.value = channels
            }
        }
    }

    fun fetchEpisodeList() {
        dataLoading.value = true
        compositeDisposable.add(networkDataManager.getEpisodeList().subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                episodeList.value = it
                dataLoading.value = false
            }, {
                dataLoading.value = false
            }))
    }

    fun fetchMangaList() {
        loadingState.value = LoadingState.LOADING
        dataLoading.value = true
        compositeDisposable.add(networkDataManager.getMangaList().subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                dataLoading.value = false
                loadingState.value = LoadingState.SUCCESS
                mangaList.value = it
            },{
                loadingState.value = LoadingState.FAILED
                dataLoading.value = false
            }))
    }

    fun fetchPageList(episodeUrl: String) {
        dataLoading.value = true
        compositeDisposable.add(
            networkDataManager.getPageList(episodeUrl).subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    pageList.value = it
                    dataLoading.value = false
                }, {
                    dataLoading.value = false
                })
        )
    }
}