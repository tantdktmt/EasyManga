package com.easymanga.data.network

import com.easymanga.data.Channel
import com.easymanga.data.ChannelData
import com.easymanga.data.Episode
import com.easymanga.data.Manga
import io.reactivex.Single
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AppNetworkDataManager @Inject constructor(private val retrofitNetworkData: NetworkDataManager.RetrofitApi) :
    NetworkDataManager {

    companion object {

        val DEBUG_SUB_TAG = "[${AppNetworkDataManager::class.java.simpleName}]"
    }

    override fun getChannels(onResult: (isSuccess: Boolean, channels: List<Channel>?) -> Unit) {
        retrofitNetworkData.getChannelList().enqueue(object : Callback<ChannelData> {

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

    override fun getEpisodeList(): Single<List<Episode>> {
        return Single.create {
            try {
                val doc = Jsoup.connect(EndPoint.MANGA_LIST_URL).get()
                val result = doc.select("table tr td a").map {
                    Episode(it.text(), it.attr("href"), getCoverUrl(it.attr("href")))
                }
                it.onSuccess(result)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    private fun getCoverUrl(url: String?): String {
        val doc = Jsoup.connect(url).get()
        return doc.select(".bbCodeImage").first().attr("src")
    }

    override fun getMangaList(): Single<List<Manga>> {
        return Single.create {
            try {
                val doc = Jsoup.connect(EndPoint.MANGA_LIST_URL).get()
                val summary = doc.select(".messageText:first-of-type").first().ownText()
                val result = doc.select(".LbImage:first-of-type").map {
                    Manga("Chie cô bé hạt tiêu", it.attr("src"), summary)
                }
                it.onSuccess(result)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }
}