package com.easymanga.ui.episodelist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.easymanga.BR
import com.easymanga.data.Episode
import com.easymanga.databinding.EpisodeItemLayoutBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class EpisodeListAdapter(private val episodes: List<Episode>) :
    RecyclerView.Adapter<EpisodeListAdapter.ViewHolder>() {

    inner class ViewHolder(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        fun bind(episode: Episode) {
            dataBinding.setVariable(BR.itemData, episode)
            dataBinding.executePendingBindings()
            itemView.onClick {
                Toast.makeText(itemView.context, "Click ${episode.url}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding = EpisodeItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(episodes[position])
    }
}