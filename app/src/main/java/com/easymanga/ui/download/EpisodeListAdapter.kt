package com.easymanga.ui.download

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.easymanga.BR
import com.easymanga.data.Episode
import com.easymanga.databinding.EpisodeItemForDownloadingLayoutBinding
import com.easymanga.ui.SharedViewModel
import org.jetbrains.anko.sdk27.coroutines.onClick

class EpisodeListAdapter(private val episodes: List<Episode>, private val viewModel: SharedViewModel) :
    RecyclerView.Adapter<EpisodeListAdapter.ViewHolder>() {

    inner class ViewHolder(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        fun bind(position: Int) {
            dataBinding.setVariable(BR.itemData, episodes[position])
            dataBinding.executePendingBindings()
            itemView.onClick {
                episodes[position].selected = !episodes[position].selected
                viewModel.refreshEpisodeList()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding =
            EpisodeItemForDownloadingLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}