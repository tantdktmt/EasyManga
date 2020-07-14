package com.easymanga.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.easymanga.data.Channel
import com.easymanga.data.Episode
import com.easymanga.data.Manga
import com.easymanga.data.Page
import com.easymanga.data.network.DownloadManager
import com.easymanga.data.network.NetworkDataManager
import com.easymanga.ui.base.BaseViewModel
import com.easymanga.util.MangaUtil
import com.easymanga.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SharedViewModel @Inject constructor(
    application: Application,
    networkDataManager: NetworkDataManager,
    downloadManager: DownloadManager,
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider
) : BaseViewModel(
    application,
    networkDataManager,
    downloadManager,
    compositeDisposable,
    schedulerProvider
) {

    val channelList = MutableLiveData<List<Channel>>()
    val episodeList = MutableLiveData<List<Episode>>()
    val mangaList = MutableLiveData<List<Manga>>()
    val pageList = MutableLiveData<ArrayList<Page>>()
    val mangaDetail = MutableLiveData<Manga>()

    val countOfSelectedEpisodes = Transformations.map(episodeList) {
        it.count {
            it.selected
        }
    }

    val totalEpisodes = Transformations.map(episodeList) {
        it.size
    }

    var selectedAllEpisode = MutableLiveData(false)

    fun fetchChannelList() {
        networkDataManager.getChannels { isSuccess, channels ->
            if (isSuccess) {
                channelList.value = channels
            }
        }
    }

    fun fetchEpisodeList() {
        loadingState.value = LoadingState.LOADING
        compositeDisposable.add(
            networkDataManager.getEpisodeList()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    episodeList.value = it
                    loadingState.value = LoadingState.SUCCESS
                }, {
                    loadingState.value = LoadingState.FAILED
                })
        )
    }

    fun fetchMangaList() {
        loadingState.value = LoadingState.LOADING
        compositeDisposable.add(
            networkDataManager.getMangaList()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    mangaList.value = it
                    loadingState.value = LoadingState.SUCCESS
                }, {
                    loadingState.value = LoadingState.FAILED
                })
        )
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

    fun getDownloadedPageList(episodeFolder: String) {
        loadingState.value = LoadingState.LOADING
        val pages = MangaUtil.getDownloadedEpisodePages(
            getApplication()
            , MangaUtil.CHIE_CO_BE_HAT_TIEU_DOWNLOADED_FOLDER, episodeFolder
        ).map {
            Page(file = it)
        }
        pageList.value = ArrayList(pages)
        loadingState.value = LoadingState.SUCCESS
    }

    fun toggleSelectedStatusExcludingDownloadedEpisodes() {
        if (selectedAllEpisode.value != null) {
            selectedAllEpisode.value = !selectedAllEpisode.value!!
            episodeList.value?.filter {
                !it.downloaded
            }?.forEach {
                it.selected = selectedAllEpisode.value!!
            }
            episodeList.value = episodeList.value
        }
    }

    fun clearSelectedStatusAllEpisodes() {
        selectedAllEpisode.value = false
        episodeList.value?.forEach {
            it.selected = selectedAllEpisode.value!!
        }
        episodeList.value = episodeList.value
    }

    fun refreshEpisodeList() {
        episodeList.value = episodeList.value
    }

    fun downloadEpisodes() {
        episodeList.value?.forEach {
            if (it.selected) {
                downloadManager.downloadEpisode(it)
            }
        }
    }
}