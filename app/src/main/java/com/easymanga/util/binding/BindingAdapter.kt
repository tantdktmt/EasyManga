package com.easymanga.util.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.easymanga.R
import com.easymanga.data.Page
import org.jetbrains.anko.backgroundResource

@BindingAdapter("app:src")
fun bindImageViewUrl(view: ImageView, url: String) {
    Glide.with(view).load(url).into(view)
}

@BindingAdapter("app:src")
fun bindImageViewDrawable(view: ImageView, iconResId: Int) {
    Glide.with(view).load(iconResId).into(view)
}

@BindingAdapter("app:pageSrc")
fun bindImageViewUrlOrFile(view: ImageView, page: Page) {
    if (page.imageUrl != null) {
        Glide.with(view).load(page.imageUrl).into(view)
    } else if (page.file != null) {
        Glide.with(view).load(page.file).into(view)
    }
}

@BindingAdapter("app:selectAllImageSrc")
fun bindSelectAllImageViewSrc(view: ImageView, selected: Boolean) {
    if (selected) {
        view.setImageResource(R.drawable.ic_check_circle_outline_checked)
    } else {
        view.setImageResource(R.drawable.ic_check_circle_outline_normal)
    }
}

@BindingAdapter("app:downloadImageSrc")
fun bindDownloadImageView(view: ImageView, enable: Boolean) {
    if (enable) {
        view.setImageResource(R.drawable.ic_file_download_checked)
    } else {
        view.setImageResource(R.drawable.ic_file_download_normal)
    }
}

@BindingAdapter("app:background")
fun bindViewBackground(view: View, selected: Boolean) {
    val backgroundResId = if (selected) R.drawable.bg_red_stroke else R.drawable.bg_gray_stroke
    view.backgroundResource = backgroundResId
}
