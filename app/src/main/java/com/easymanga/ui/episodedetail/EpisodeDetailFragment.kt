package com.easymanga.ui.episodedetail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.easymanga.EasyMangaApplication
import com.easymanga.data.Page
import com.easymanga.databinding.FragmentEpisodeDetailBinding
import com.easymanga.ui.base.BaseFragment
import com.easymanga.util.Constant
import kotlinx.android.synthetic.main.fragment_episode_detail.*
import javax.inject.Inject

class EpisodeDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: FragmentEpisodeDetailBinding

    private val pages = ArrayList<Page>()

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
                viewmodel =
                    ViewModelProvider(this@EpisodeDetailFragment, viewModelFactory).get(
                        EpisodeDetailViewModel::class.java
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
        rv_page.apply {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            this.adapter = EpisodeDetailAdapter(pages)
        }
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.pageListLive?.observe(viewLifecycleOwner, Observer {
            if (Constant.IS_DEBUG_MODE) {
                Log.d(Constant.LOG_TAG, "Page list: $it")
            }
            pages.addAll(it)
            rv_page.adapter?.notifyDataSetChanged()
        })
    }

    override fun onResume() {
        super.onResume()
        // TESTING
        val FAKE_URL = "http://webtrainghiem.com/threads/truyen-tranh-chie-co-be-hat-tieu-jarinko-chie-tap-1.7239/"
        // TESTING
        val args: EpisodeDetailFragmentArgs by navArgs()
        // TODO: update to use safeArgs
        val episodeUrl = args.episodeUrl
        viewDataBinding.viewmodel?.fetchPageList(FAKE_URL)
    }
}
