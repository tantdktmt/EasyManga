package com.easymanga.ui.download

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.easymanga.BuildConfig
import com.easymanga.EasyMangaApplication
import com.easymanga.R
import com.easymanga.data.Episode
import com.easymanga.databinding.FragmentDownloadBinding
import com.easymanga.ui.base.BaseFragment
import com.easymanga.util.CommonUtil
import com.easymanga.util.Constant
import com.easymanga.util.MangaUtil
import kotlinx.android.synthetic.main.fragment_download.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.toast

class DownloadFragment : BaseFragment() {

    private lateinit var viewDataBinding: FragmentDownloadBinding

    private val episodes = ArrayList<Episode>()

    override fun onAttach(context: Context) {
        EasyMangaApplication.getInstance().getAppComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentDownloadBinding.inflate(inflater, container, false)
            .apply {
                viewmodel = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        layout_download.onClick {
            viewModel.downloadEpisodes()
            toast(R.string.download_episodesdownloadednoti)
            navController.popBackStack()
        }
    }

    private fun setupRecyclerView() {
        rv_chapter.apply {
            layoutManager = GridLayoutManager(context, 3)
            itemAnimator = DefaultItemAnimator()
            this.adapter = EpisodeListAdapter(episodes, viewModel)
        }
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.episodeList?.observe(viewLifecycleOwner, Observer {
            if (BuildConfig.DEBUG) {
                Log.d(Constant.LOG_TAG, "Episode list: $it")
            }
            episodes.clear()
            episodes.addAll(it)
            updateDownloadedStatus()
            rv_chapter.adapter?.notifyDataSetChanged()
        })
    }

    private fun updateDownloadedStatus() {
        val downloadedEpisodes = MangaUtil.getDownloadedEpisodeList(
            requireContext(),
            MangaUtil.CHIE_CO_BE_HAT_TIEU_DOWNLOADED_FOLDER
        )
        episodes.forEach {
            val number = it.number
            it.downloaded = downloadedEpisodes.any { it.number == number }
        }
    }

    override fun onResume() {
        super.onResume()
        if (CommonUtil.isListEmpty(viewDataBinding.viewmodel?.episodeList?.value)) {
            viewDataBinding.viewmodel?.fetchEpisodeList()
        } else {
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearSelectedStatusAllEpisodes()
    }
}
