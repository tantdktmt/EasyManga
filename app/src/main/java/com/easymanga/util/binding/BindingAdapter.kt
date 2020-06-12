package com.easymanga.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:src")
fun bindImageViewUrl(view: ImageView, url: String) {
    Glide.with(view).load(url).into(view)
}