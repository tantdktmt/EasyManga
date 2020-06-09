package com.easymanga.data.network

import android.util.Log
import com.easymanga.data.model.Channel
import com.easymanga.data.model.ChannelData
import com.easymanga.util.Constant
import retrofit2.Call
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
}