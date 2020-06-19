package com.easymanga.ui.mangadetail.page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.easymanga.BR
import com.easymanga.data.Episode
import com.easymanga.databinding.EpisodeItemLayoutBinding
import com.easymanga.ui.mangadetail.MangaDetailFragmentDirections
import org.jetbrains.anko.sdk27.coroutines.onClick

class EpisodeListAdapter(private val episodes: List<Episode>) :
    RecyclerView.Adapter<EpisodeListAdapter.ViewHolder>() {

    inner class ViewHolder(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        fun bind(episode: Episode) {
            dataBinding.setVariable(BR.itemData, episode)
            dataBinding.executePendingBindings()
            itemView.onClick {
                val action =
                    MangaDetailFragmentDirections.goToEpisodeDetailAction(
                        episode.url
                    )
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding =
            EpisodeItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(episodes[position])
    }
}