package com.easymanga.util

import android.util.Log
import java.io.File
import java.io.FileFilter

class FileUtil {

    companion object {

        fun getSubDirectories(path: String): Array<File> {

            Log.d(Constant.SPECIAL_LOG_TAG, "getSubDirectory, ${File(path).exists()}")
            return if (!File(path).exists()) {
                emptyArray()
            } else {
                File(path).listFiles(FileFilter {
                    it.isDirectory
                })
            }
        }
    }
}