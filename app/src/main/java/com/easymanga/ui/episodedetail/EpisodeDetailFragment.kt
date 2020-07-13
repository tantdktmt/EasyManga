package com.easymanga.ui.episodedetail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.easymanga.BuildConfig
import com.easymanga.EasyMangaApplication
import com.easymanga.data.Page
import com.easymanga.databinding.FragmentEpisodeDetailBinding
import com.easymanga.ui.base.BaseFragment
import com.easymanga.util.CommonUtil
import com.easymanga.util.Constant
import kotlinx.android.synthetic.main.fragment_episode_detail.*

class EpisodeDetailFragment : BaseFragment() {

    private lateinit var viewDataBinding: FragmentEpisodeDetailBinding

    private val pages = ArrayList<Page>()

    private val args: EpisodeDetailFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        EasyMangaApplication.getInstance().getAppComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentEpisodeDetailBinding.inflate(inflater, container, false)
            .apply {
                viewmodel = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setupObservers()
    }

    private fun setUpRecyclerView() {
        rv_page.apply {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            this.adapter = EpisodeDetailAdapter(pages)
        }
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.pageList?.observe(viewLifecycleOwner, Observer {
            if (BuildConfig.DEBUG) {
                Log.d(Constant.LOG_TAG, "Page list: $it")
            }
            pages.clear()
            pages.addAll(it)
            rv_page.adapter?.notifyDataSetChanged()
        })
    }

    override fun onResume() {
        super.onResume()
        val episodeUrl = args.episodeUrl
        val episodeFolder = args.episodeFolder
        if (episodeUrl != null
            && CommonUtil.isListEmpty(viewDataBinding.viewmodel?.pageList?.value)
        ) {
            viewDataBinding.viewmodel?.fetchPageList(episodeUrl)
        } else if (episodeFolder != null
            && CommonUtil.isListEmpty(viewDataBinding.viewmodel?.pageList?.value)
        ) {
            viewDataBinding.viewmodel?.getDownloadedPageList(episodeFolder)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewDataBinding.viewmodel?.pageList?.value?.clear()
    }
}
