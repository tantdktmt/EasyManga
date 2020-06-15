package com.easymanga.ui.mangalist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.easymanga.BR
import com.easymanga.R
import com.easymanga.data.Manga
import com.easymanga.databinding.MangaItemLayoutBinding
import org.jetbrains.anko.sdk27.coroutines.onClick

class MangaListAdapter(private val mangas: List<Manga>) :
    RecyclerView.Adapter<MangaListAdapter.ViewHolder>() {

    inner class ViewHolder(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        fun bind(manga: Manga) {
            dataBinding.setVariable(BR.itemData, manga)
            dataBinding.executePendingBindings()
            itemView.onClick {
                itemView.findNavController().navigate(R.id.go_to_episode_list_action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding = MangaItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return mangas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mangas[position])
    }
}