package com.easymanga.ui.storage.page

import android.view.LayoutInflater
import android.view.ViewGroup
import com.easymanga.data.DownloadedManga
import com.easymanga.data.Episode
import com.easymanga.databinding.ItemEpisodeDownloadedBinding
import com.easymanga.databinding.ItemMangaDownloadedBinding
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class DownloadedAdapter(groups: List<out ExpandableGroup<Episode>>) :

    ExpandableRecyclerViewAdapter<MangaViewHolder, EpisodeViewHolder>(groups) {

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val dataBinding =
            ItemMangaDownloadedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MangaViewHolder(dataBinding)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val dataBinding =
            ItemEpisodeDownloadedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return EpisodeViewHolder(dataBinding)
    }

    override fun onBindChildViewHolder(
        holder: EpisodeViewHolder?,
        flatPosition: Int,
        group: ExpandableGroup<*>?,
        childIndex: Int
    ) {
        val episode = group?.items?.get(childIndex) as Episode
        holder?.bind(episode)
    }

    override fun onBindGroupViewHolder(
        holder: MangaViewHolder?,
        flatPosition: Int,
        group: ExpandableGroup<*>?
    ) {
        holder?.bind(group as DownloadedManga)
    }
}
