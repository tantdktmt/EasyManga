package com.easymanga.data.network

import com.easymanga.data.*
import io.reactivex.Single
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNetworkDataManager @Inject constructor(
    private val retrofitNetworkData: NetworkDataManager.RetrofitApi
) :
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

    override fun getEpisodeList(): Single<ArrayList<Episode>> {
        return Single.create {
            try {
                val doc = Jsoup.connect(EndPoint.MANGA_LIST_URL).get()
                var chapterNumber = 0
                val result = doc.select("table tr td a").map {
                    Episode(
                        it.text(),
                        ++chapterNumber,
                        it.attr("href"),
                        getCoverUrl(it.attr("href")),
                        false
                    )
                }
                it.onSuccess(ArrayList(result))
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    private fun getCoverUrl(episodeUrl: String?): String {
        val doc = Jsoup.connect(episodeUrl).get()
        return doc.select(".bbCodeImage").first().attr("src")
    }

    override fun getMangaList(): Single<ArrayList<Manga>> {
        return Single.create {
            try {
                val doc = Jsoup.connect(EndPoint.MANGA_LIST_URL).get()
                val summary = doc.select(".messageText:first-of-type").first().ownText()
                val result = ArrayList(doc.select(".LbImage:first-of-type").map {
                    Manga("Chie cô bé hạt tiêu", it.attr("src"), summary)
                })
                result.add(Manga("7 viên ngọc rồng"
                    , "https://znews-photo.zadn.vn/w660/Uploaded/oplukaa/2020_06_14/100384771_10157509824368869_9057397775934685184_o.jpg"))
                result.add(Manga("Dấu ấn rồng thiêng"
                    , "https://upload.wikimedia.org/wikipedia/vi/thumb/e/e2/Dragon_Ques_vol_26.jpg/230px-Dragon_Ques_vol_26.jpg"))
                result.add(Manga("Ninja loạn thị"
                    , "https://metruyentranh.com/images/covers/ninja-loan-thi.jpg"))
                result.add(Manga("Dũng sĩ Hesman"
                    , "https://www.dtv-ebook.com/images/files_2/2020/dung-si-hesman.jpg"))
                it.onSuccess(result)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    override fun getPageList(episodeUrl: String): Single<ArrayList<Page>> {
        return Single.create {
            try {
                val doc = Jsoup.connect(episodeUrl).get()
                val result = doc.select(".bbCodeImage").map {
                    Page(it.attr("src"))
                }
                it.onSuccess(ArrayList(result))
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }
}