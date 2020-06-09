package com.easymanga.data.network

import com.easymanga.data.model.Channel
import com.easymanga.data.model.ChannelData
import retrofit2.Call
import retrofit2.http.GET

interface ApiManager {

    fun getChannels(): List<Channel>?

    interface RetrofitApi {

        @GET("client/channels/list?num_per_page=0&order_type=1&sort_type=1&package_code=0&channel_type=1")
        fun getChannelList(): Call<ChannelData>
    }
}