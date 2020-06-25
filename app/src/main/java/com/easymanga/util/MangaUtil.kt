package com.easymanga.util

import android.content.Context
import com.easymanga.data.Manga

class MangaUtil {

    companion object {

        fun getDownloadedMangaList(context: Context): List<Manga> {
            val path = CommonUtil.getRootDirPath(context)
            return FileUtil.getSubDirectories(path).map {
                Manga(it.name)
            }
        }
    }
}