package com.easymanga.util

class CommonUtil {

    companion object {

        fun isListEmpty(list: List<*>?): Boolean {
            return list == null || list.isEmpty()
        }
    }
}