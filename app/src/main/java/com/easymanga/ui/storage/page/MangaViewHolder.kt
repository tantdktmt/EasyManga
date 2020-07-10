package com.easymanga.ui.storage.page

import android.view.animation.AnimationUtils
import androidx.databinding.ViewDataBinding
import com.easymanga.BR
import com.easymanga.R
import com.easymanga.data.DownloadedManga
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import kotlinx.android.synthetic.main.item_manga_downloaded.view.*

class MangaViewHolder(private val dataBinding: ViewDataBinding) :
    GroupViewHolder(dataBinding.root) {

    fun bind(manga: DownloadedManga) {
        dataBinding.setVariable(BR.manga, manga)
        dataBinding.executePendingBindings()
    }

    override fun expand() {
        animateExpand()
    }

    override fun collapse() {
        animateCollapse()
    }

    fun animateExpand() {
        val rotation = AnimationUtils.loadAnimation(itemView.context, R.anim.clockwise_rotation)
        rotation.fillAfter = true
        itemView.iv_arrow.animation = rotation
    }

    fun animateCollapse() {
        val rotation = AnimationUtils.loadAnimation(itemView.context, R.anim.alticlockwise_rotation)
        rotation.fillAfter = true
        itemView.iv_arrow.animation = rotation
    }
}