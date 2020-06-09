package com.easymanga.data.network

import android.util.Log
import com.easymanga.data.Channel
import com.easymanga.data.ChannelData
import com.easymanga.util.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class AppApiManager @Inject constructor(private val retrofitApi: ApiManager.RetrofitApi) :
    ApiManager {

    companion object {

        val DEBUG_SUB_TAG = "[${AppApiManager::class.java.simpleName}]"
    }

    override fun getChannels(): List<Channel>? {
        val call: Call<ChannelData> = retrofitApi.getChannelList()
        try {
            val response: Response<ChannelData> = call.execute()
            if (Constant.IS_DEBUG_MODE) {
                Log.d(
                    Constant.LOG_TAG, "$DEBUG_SUB_TAG getChannels, " +
                            "responseCode=${response.code()}, message=${response.message()}"
                )
            }
            if (response.isSuccessful) {
                return response.body()?.data
            } else {
                return emptyList()
            }
        } catch (e: IOException) {
            if (Constant.IS_DEBUG_MODE) {
                Log.d(
                    Constant.LOG_TAG, "$DEBUG_SUB_TAG error: $e"
                )
            }
            return emptyList()
        }
    }

    override fun getChannels2(onResult: (isSuccess: Boolean, channels: List<Channel>?) -> Unit) {
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