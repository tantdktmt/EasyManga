package com.easymanga.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import kotlinx.android.parcel.Parcelize

data class Channel(

    @Expose val channelId: Int,
    @Expose val channelName: String,
    @Expose val channelNo: Int,
    @Expose val channelGenre: String,
    @Expose val videoType: Int,
    @Expose val encryption: Int,
    @Expose val timeShift: Int,
    @Expose val catchUp: Int,
    @Expose val locator: String,
    @Expose val pvr: Int,
    @Expose val logo: Int,
    @Expose val logoUrl: String,
    @Expose val packageCode: String,
    @Expose val time: String
)

data class ChannelData(

    @Expose val data: List<Channel>,
    @Expose val total: Int
)

@Parcelize
data class Manga(val name: String, val imageUrl: String = "", val summary: String = "") : Parcelable

class DownloadedManga(name: String, val iconResId: Int, episodes: List<Episode>) :
    ExpandableGroup<Episode>(name, episodes) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DownloadedManga

        if (iconResId != other.iconResId) return false

        return true
    }

    override fun hashCode(): Int {
        return iconResId
    }

    override fun toString(): String {
        return "DownloadedManga(iconResId=$iconResId, name=$title, episodes=$items)"
    }
}

@Parcelize
data class Episode(
    val name: String = "",
    val number: Int,
    val url: String = "",
    val coverUrl: String = "",
    var selected: Boolean = false,
    var downloaded: Boolean = false
) : Parcelable

data class Page(val imageUrl: String)
