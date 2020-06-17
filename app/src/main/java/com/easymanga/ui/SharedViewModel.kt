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
        networkDataManager.getChannels { isSuccess, channels ->
            if (isSuccess) {
                channelList.value = channels
            }
        }
    }

    fun fetchEpisodeList() {
        loadingState.value = LoadingState.LOADING
        compositeDisposable.add(networkDataManager.getEpisodeList().subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                episodeList.value = it
                loadingState.value = LoadingState.SUCCESS
            }, {
                loadingState.value = LoadingState.FAILED
            }))
    }

    fun fetchMangaList() {
        loadingState.value = LoadingState.LOADING
        compositeDisposable.add(networkDataManager.getMangaList().subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                mangaList.value = it
                loadingState.value = LoadingState.SUCCESS
            },{
                loadingState.value = LoadingState.FAILED
            }))
    }

    fun fetchPageList(episodeUrl: String) {
        loadingState.value = LoadingState.LOADING
        compositeDisposable.add(
            networkDataManager.getPageList(episodeUrl).subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    pageList.value = it
                    loadingState.value = LoadingState.SUCCESS
                }, {
                    loadingState.value = LoadingState.FAILED
                })
        )
    }
}