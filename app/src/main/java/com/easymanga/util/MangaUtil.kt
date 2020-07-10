package com.easymanga.util

import android.content.Context
import com.easymanga.data.Episode
import com.easymanga.data.Manga
import java.io.File

class MangaUtil {

    companion object {

        const val CHIE_CO_BE_HAT_TIEU_DOWNLOADED_FOLDER = "Chie co be hat tieu"

        fun getDownloadedMangaList(context: Context): List<Manga> {
            val path = CommonUtil.getRootDirPath(context)
            return FileUtil.getSubDirectories(path).map {
                Manga(it.name)
            }
        }

        fun getDownloadedEpisodeList(context: Context, mangaFolder: String): List<Episode> {
            val path = "${CommonUtil.getRootDirPath(context)}${File.separator}$mangaFolder"
            return FileUtil.getSubDirectories(path).map {
                Episode(number = it.name.toInt())
            }
        }

        fun getDownloadedEpisodePages(
            context: Context,
            mangaFolder: String,
            episodeFolder: Int
        ): List<File> {
            val path =
                "${CommonUtil.getRootDirPath(context)}${File.separator}$mangaFolder${File.separator}$episodeFolder"
            return FileUtil.getSubFiles(path)
        }
    }
}