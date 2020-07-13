package com.easymanga.ui.storage.page

import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import com.easymanga.BR
import com.easymanga.data.Episode
import com.easymanga.ui.storage.StorageFragmentDirections
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import org.jetbrains.anko.sdk27.coroutines.onClick

class EpisodeViewHolder(private val dataBinding: ViewDataBinding) :
    ChildViewHolder(dataBinding.root) {

    fun bind(episode: Episode) {
        dataBinding.setVariable(BR.episode, episode)
        dataBinding.executePendingBindings()
        itemView.onClick {
            val action = StorageFragmentDirections.goToEpisodeDetailAction(episodeFolder = episode.number.toString())
            itemView.findNavController().navigate(action)
        }
    }
}