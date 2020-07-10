package com.easymanga.ui.storage.page

import android.util.Log
import androidx.databinding.ViewDataBinding
import com.easymanga.BR
import com.easymanga.data.Episode
import com.easymanga.util.Constant
import com.easymanga.util.MangaUtil
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import org.jetbrains.anko.sdk27.coroutines.onClick

class EpisodeViewHolder(private val dataBinding: ViewDataBinding) :
    ChildViewHolder(dataBinding.root) {

    fun bind(episode: Episode) {
        dataBinding.setVariable(BR.episode, episode)
        dataBinding.executePendingBindings()
        itemView.onClick {
            val pages = MangaUtil.getDownloadedEpisodePages(itemView.context
                , MangaUtil.CHIE_CO_BE_HAT_TIEU_DOWNLOADED_FOLDER, episode.number)
        }
    }
}