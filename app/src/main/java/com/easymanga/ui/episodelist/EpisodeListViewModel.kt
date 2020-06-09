package com.easymanga.ui.episodelist

import androidx.lifecycle.MutableLiveData
import com.easymanga.data.Channel
import com.easymanga.data.network.ApiManager
import com.easymanga.ui.base.BaseViewModel
import javax.inject.Inject

class EpisodeListViewModel @Inject constructor(private val apiManager: ApiManager) : BaseViewModel() {

    val channelListLive = MutableLiveData<List<Channel>>()

    fun fetchChannelList() {
        dataLoading.value = true
        apiManager.getChannels2 { isSuccess, channels ->
            dataLoading.value = false
            if (isSuccess) {
                channelListLive.value = channels
            }
        }
    }
}