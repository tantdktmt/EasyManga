package com.easymanga.data

import com.google.gson.annotations.Expose

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

data class Manga(val name: String, val imageUrl: String, val summary: String)

data class Episode(val name: String, val url: String)