package com.easymanga.ui.episodelist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.easymanga.EasyMangaApplication
import com.easymanga.data.Episode
import com.easymanga.databinding.FragmentEpisodeListBinding
import com.easymanga.ui.base.BaseFragment
import com.easymanga.util.Constant
import kotlinx.android.synthetic.main.fragment_episode_list.*
import javax.inject.Inject

class EpisodeListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: FragmentEpisodeListBinding

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
        viewDataBinding = FragmentEpisodeListBinding.inflate(inflater, container, false)
            .apply {
                viewmodel =
                    ViewModelProvider(this@EpisodeListFragment, viewModelFactory).get(
                        EpisodeListViewModel::class.java
                    )
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
            this.adapter = EpisodeListAdapter(episodes)
        }
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.episodeListLive?.observe(viewLifecycleOwner, Observer {
            if (Constant.IS_DEBUG_MODE) {
                Log.d(Constant.LOG_TAG, "Episode list: $it")
            }
            episodes.clear()
            episodes.addAll(it)
            rv_episode.adapter?.notifyDataSetChanged()
        })
    }

    override fun onResume() {
        super.onResume()
        if (viewDataBinding.viewmodel?.episodeListLive?.value == null
            || viewDataBinding.viewmodel?.episodeListLive?.value!!.isEmpty()
        ) {
            viewDataBinding.viewmodel?.fetchEpisodeList()
        }
    }
}
