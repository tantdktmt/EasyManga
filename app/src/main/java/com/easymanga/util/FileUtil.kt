package com.easymanga.util

import java.io.File
import java.io.FileFilter

class FileUtil {

    companion object {

        fun getSubDirectories(path: String): Array<File> {
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