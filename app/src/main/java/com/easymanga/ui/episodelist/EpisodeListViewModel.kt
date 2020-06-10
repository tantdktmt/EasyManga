package com.easymanga.ui.episodelist

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.easymanga.R
import com.easymanga.data.Channel
import com.easymanga.data.Episode
import com.easymanga.data.network.NetworkDataManager
import com.easymanga.ui.base.BaseViewModel
import com.easymanga.util.Constant
import com.easymanga.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class EpisodeListViewModel @Inject constructor(
    networkDataManager: NetworkDataManager,
    compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider
) : BaseViewModel(networkDataManager, compositeDisposable, schedulerProvider) {

    val channelListLive = MutableLiveData<List<Channel>>()
    val episodeListLive = MutableLiveData<List<Episode>>()

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
}