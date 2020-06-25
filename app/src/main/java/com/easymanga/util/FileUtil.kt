package com.easymanga.util

import java.io.File
import java.io.FileFilter

class FileUtil {

    companion object {

        fun getSubDirectories(path: String): Array<File> {
            return File(path).listFiles(FileFilter {
                it.isDirectory
            })
        }
    }
}