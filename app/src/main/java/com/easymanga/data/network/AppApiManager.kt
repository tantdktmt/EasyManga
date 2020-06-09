package com.easymanga.data.network

import com.easymanga.data.Channel
import com.easymanga.data.ChannelData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AppApiManager @Inject constructor(private val retrofitApi: ApiManager.RetrofitApi) :
    ApiManager {

    companion object {

        val DEBUG_SUB_TAG = "[${AppApiManager::class.java.simpleName}]"
    }

    override fun getChannels(onResult: (isSuccess: Boolean, channels: List<Channel>?) -> Unit) {
        retrofitApi.getChannelList().enqueue(object : Callback<ChannelData> {

            override fun onFailure(call: Call<ChannelData>, t: Throwable) {
                onResult(false, null)
            }

            override fun onResponse(call: Call<ChannelData>, response: Response<ChannelData>) {
                if (response.isSuccessful) {
                    onResult(true, response.body()?.data)
                } else {
                    onResult(false, null)
                }
            }
        })
    }
}