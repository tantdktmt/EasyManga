package com.easymanga.ui.storage.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.easymanga.R
import com.easymanga.data.DownloadedManga
import com.easymanga.databinding.FragmentDownloadedBinding
import com.easymanga.ui.base.BaseFragment
import com.easymanga.util.MangaUtil
import kotlinx.android.synthetic.main.fragment_downloaded.*

class DownloadedFragment : BaseFragment() {

    private lateinit var viewDataBinding: FragmentDownloadedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentDownloadedBinding.inflate(inflater, container, false)
            .apply {
                viewmodel = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_downloaded.apply {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
        }
        loadData()
    }

    private fun loadData() {
        val downloadedMangas =
            MangaUtil.getDownloadedMangaList(requireContext())
                .map {
                    val episodes = MangaUtil.getDownloadedEpisodeList(requireContext(), it.name)
                    DownloadedManga(it.name, R.drawable.downloaded_manga_thumbnail, episodes)
                }
        rv_downloaded.adapter =
            DownloadedAdapter(downloadedMangas)
    }
}
