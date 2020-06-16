package com.easymanga.ui.episodelist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.easymanga.databinding.FragmentMangaDetailBinding
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

    private lateinit var viewDataBinding: FragmentMangaDetailBinding

    private val TABS = arrayOf("Chi tiết", "Chapters")

    private val args: MangaDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(Constant.LOG_TAG, "$DEBUG_SUB_TAG argument=${args.mangaDetail}")
        viewModel.mangaDetail.value = args.mangaDetail
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentMangaDetailBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vp_main.adapter = SimpleViewPagerAdapter(requireActivity())
        vp_main.setPageTransformer(ZoomOutPageTransformer())
        TabLayoutMediator(tabs, vp_main) { tab, position ->
            tab.text = TABS[position]
        }.attach()
    }
}
