package com.easymanga.ui.episodelist.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.easymanga.databinding.FragmentMangaOverviewBinding
import com.easymanga.ui.base.BaseFragment

class MangaOverviewFragment : BaseFragment() {

    private lateinit var viewDataBinding: FragmentMangaOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentMangaOverviewBinding.inflate(inflater, container, false)
        viewDataBinding.manga = Manga()
        viewDataBinding.manga?.summary = "Hehe"
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
