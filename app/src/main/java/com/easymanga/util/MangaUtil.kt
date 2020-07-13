package com.easymanga.util

import android.content.Context
import com.easymanga.data.Episode
import com.easymanga.data.Manga
import java.io.File
import java.util.*

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
            episodeFolder: String
        ): List<File> {
            val path =
                "${CommonUtil.getRootDirPath(context)}${File.separator}$mangaFolder${File.separator}$episodeFolder"
            val files = FileUtil.getSubFiles(path)
            Collections.sort(
                files
            ) { file1, file2 ->
                file1.name.substringBefore(".").toInt() - file2.name.substringBefore(".").toInt()
            }
            return files
        }
    }
}