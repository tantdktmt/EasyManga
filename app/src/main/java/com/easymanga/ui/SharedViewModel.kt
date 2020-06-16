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

    val channelListLive = MutableLiveData<List<Channel>>()
    val episodeListLive = MutableLiveData<List<Episode>>()
    val mangaListLive = MutableLiveData<List<Manga>>()
    val pageListLive = MutableLiveData<List<Page>>()
    val mangaDetail = MutableLiveData<Manga>()

    fun fetchChannelList() {
        dataLoading.value = true
        networkDataManager.getChannels { isSuccess, channels ->
            dataLoading.value = false
            if (isSuccess) {
                channelListLive.value = channels
            }
        }
    }

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

    fun fetchMangaList() {
        dataLoading.value = true
        compositeDisposable.add(networkDataManager.getMangaList().subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                dataLoading.value = false
                mangaListLive.value = it
            },{
                dataLoading.value = false
            }))
    }

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