package com.easymanga.data.network

import com.easymanga.data.*
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface NetworkDataManager {

    fun getChannels(onResult: (isSuccess: Boolean, channels: List<Channel>?) -> Unit)

    fun getEpisodeList(): Single<ArrayList<Episode>>

    fun getMangaList(): Single<ArrayList<Manga>>

    fun getPageList(episodeUrl: String): Single<ArrayList<Page>>

    interface RetrofitApi {

        @GET("client/channels/list?num_per_page=0&order_type=1&sort_type=1&package_code=0&channel_type=1")
        fun getChannelList(): Call<ChannelData>
    }
}