package com.easymanga.data.network

import android.content.Context
import android.util.Log
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.easymanga.BuildConfig
import com.easymanga.data.Episode
import com.easymanga.data.Page
import com.easymanga.util.CommonUtil
import com.easymanga.util.Constant
import com.easymanga.util.rx.SchedulerProvider
import java.io.File
import java.util.*
import javax.inject.Inject

class DownloadManager @Inject constructor(
    private val networkDataManager: NetworkDataManager,
    private val schedulerProvider: SchedulerProvider,
    private val applicationContext: Context
) {

    companion object {

        val DEBUG_SUB_TAG = "[${DownloadManager::class.java.simpleName}]"
    }

    private val DIR_PATH = CommonUtil.getRootDirPath(applicationContext)
    private var downloadId: Int = 0

    fun downloadEpisode(episode: Episode) {
        if (BuildConfig.DEBUG) {
            Log.d(
                Constant.LOG_TAG,
                "$DEBUG_SUB_TAG downloadEpisode, episode=$episode"
            )
        }
        networkDataManager.getPageList(episode.url).subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                downloadPages("Chie co be hat tieu", episode.number, it)
            }, {
                if (BuildConfig.DEBUG) {
                    Log.d(
                        Constant.LOG_TAG,
                        "$DEBUG_SUB_TAG downloadEpisode error"
                    )
                }
            })
    }

    private fun downloadPages(
        mangaDownloadFolder: String,
        episodeNumber: Int,
        pages: ArrayList<Page>
    ) {
        val downloadFolder =
            "$DIR_PATH${File.separator}$mangaDownloadFolder${File.separator}$episodeNumber${File.separator}"
        for (i in pages.indices) {
            downloadPage(downloadFolder, pages[i].imageUrl, "$i.jpg")
        }
    }

    private fun downloadPage(downloadFolder: String, url: String, fileName: String) {
        downloadId = PRDownloader.download(url, downloadFolder, fileName)
            .build()
            .setOnStartOrResumeListener {
                if (BuildConfig.DEBUG) {
                    Log.d(
                        Constant.LOG_TAG,
                        "$DEBUG_SUB_TAG downloadPage id $downloadId, onStartOrResume"
                    )
                }
            }
            .setOnPauseListener {
                if (BuildConfig.DEBUG) {
                    Log.d(
                        Constant.LOG_TAG,
                        "$DEBUG_SUB_TAG downloadPage id $downloadId, onPause"
                    )
                }
            }
            .setOnCancelListener {
                if (BuildConfig.DEBUG) {
                    Log.d(
                        Constant.LOG_TAG,
                        "$DEBUG_SUB_TAG downloadPage id $downloadId, onCancel"
                    )
                }
                downloadId = 0
            }
            .setOnProgressListener {
                val progressPercent = it.currentBytes * 100 / it.totalBytes
//                if (BuildConfig.DEBUG) {
//                    Log.d(
//                        Constant.LOG_TAG,
//                        "${AppNetworkDataManager.DEBUG_SUB_TAG} downloadPage id $downloadId, onProgress, percent=$progressPercent"
//                    )
//                }
            }
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    if (BuildConfig.DEBUG) {
                        Log.d(
                            Constant.LOG_TAG,
                            "$DEBUG_SUB_TAG downloadPage id $downloadId, complete"
                        )
                    }
                }

                override fun onError(error: Error?) {
                    if (BuildConfig.DEBUG) {
                        Log.d(
                            Constant.LOG_TAG,
                            "$DEBUG_SUB_TAG downloadPage id $downloadId, error"
                        )
                    }
                }
            })
    }
}