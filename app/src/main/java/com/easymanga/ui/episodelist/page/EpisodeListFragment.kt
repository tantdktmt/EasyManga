package com.easymanga.ui.episodelist.page

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
import com.easymanga.databinding.FragmentEpisodeListBinding
import com.easymanga.ui.base.BaseFragment
import com.easymanga.ui.base.BaseViewModel
import com.easymanga.util.CommonUtil
import com.easymanga.util.Constant
import com.easymanga.util.DialogUtil
import kotlinx.android.synthetic.main.fragment_episode_list.*

class EpisodeListFragment : BaseFragment() {

    private lateinit var viewDataBinding: FragmentEpisodeListBinding

    private val episodes = ArrayList<Episode>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        EasyMangaApplication.getInstance().getAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentEpisodeListBinding.inflate(inflater, container, false)
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
        rv_episode.apply {
            layoutManager = GridLayoutManager(context, 2)
            itemAnimator = DefaultItemAnimator()
            this.adapter =
                EpisodeListAdapter(episodes)
        }
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.episodeList?.observe(viewLifecycleOwner, Observer {
            if (BuildConfig.DEBUG) {
                Log.d(Constant.LOG_TAG, "Episode list: $it")
            }
            episodes.clear()
            episodes.addAll(it)
            rv_episode.adapter?.notifyDataSetChanged()
        })

        viewDataBinding.viewmodel?.loadingState?.observe(viewLifecycleOwner, Observer {
            if (it == BaseViewModel.LoadingState.IDLE) {
                DialogUtil.showMessageDialog(requireContext(), R.string.alert_error_message)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        if (CommonUtil.isListEmpty(viewDataBinding.viewmodel?.episodeList?.value)) {
            viewDataBinding.viewmodel?.fetchEpisodeList()
        }
    }
}
