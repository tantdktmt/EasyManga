package com.easymanga.ui.episodedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.easymanga.BR
import com.easymanga.data.Page
import com.easymanga.databinding.ItemEpisodeDetailBinding

class EpisodeDetailAdapter(private val pages: List<Page>) :
    RecyclerView.Adapter<EpisodeDetailAdapter.ViewHolder>() {

    inner class ViewHolder(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        fun bind(page: Page) {
            dataBinding.setVariable(BR.itemData, page)
            dataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding =
            ItemEpisodeDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pages[position])
    }
}