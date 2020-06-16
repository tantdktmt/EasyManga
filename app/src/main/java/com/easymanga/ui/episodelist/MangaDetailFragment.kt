package com.easymanga.ui.episodelist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.easymanga.R
import com.easymanga.ui.base.BaseFragment
import com.easymanga.ui.episodelist.page.SimpleViewPagerAdapter
import com.easymanga.ui.episodelist.page.ZoomOutPageTransformer
import com.easymanga.util.Constant
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_manga_detail.*

class MangaDetailFragment : BaseFragment() {

    companion object {

        private val DEBUG_SUB_TAG = "[${MangaDetailFragment::class.java.simpleName}]"
    }

    private val TABS = arrayOf("Chi tiết", "Chapters")

    private val args: MangaDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_manga_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vp_main.adapter = SimpleViewPagerAdapter(requireActivity())
        vp_main.setPageTransformer(ZoomOutPageTransformer())
        TabLayoutMediator(tabs, vp_main) { tab, position ->
            tab.text = TABS[position]
        }.attach()

        Log.d(Constant.LOG_TAG, "$DEBUG_SUB_TAG argument=${args.mangaDetail}")
    }
}
