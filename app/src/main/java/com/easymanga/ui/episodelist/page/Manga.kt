package com.easymanga.ui.episodelist.page

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.easymanga.BR

class Manga : BaseObservable() {

    var summary: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.summary)
        }
}