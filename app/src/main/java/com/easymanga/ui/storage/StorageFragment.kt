package com.easymanga.ui.storage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.easymanga.EasyMangaApplication
import com.easymanga.databinding.FragmentStorageBinding
import com.easymanga.ui.base.BaseFragment
import com.easymanga.ui.base.ZoomOutPageTransformer
import com.easymanga.ui.storage.page.StoragePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_manga_detail.tabs
import kotlinx.android.synthetic.main.fragment_storage.*

class StorageFragment : BaseFragment() {

    private lateinit var viewDataBinding: FragmentStorageBinding

    private val TABS = arrayOf("Tải xuống", "Lịch sử")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        EasyMangaApplication.getInstance().getAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentStorageBinding.inflate(inflater, container, false)
            .apply {
                viewmodel = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.adapter = StoragePagerAdapter(requireActivity())
        view_pager.setPageTransformer(ZoomOutPageTransformer())
        TabLayoutMediator(tabs, view_pager) { tab, position ->
            tab.text = TABS[position]
        }.attach()
    }
}
