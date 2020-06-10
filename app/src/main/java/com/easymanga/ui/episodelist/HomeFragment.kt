package com.easymanga.ui.episodelist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.easymanga.EasyMangaApplication
import com.easymanga.R
import com.easymanga.data.Channel
import com.easymanga.data.Episode
import com.easymanga.databinding.FragmentHomeBinding
import com.easymanga.ui.base.BaseFragment
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: FragmentHomeBinding

    override fun onAttach(context: Context) {
        EasyMangaApplication.getInstance().getAppComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentHomeBinding.inflate(inflater, container, false)
            .apply {
                viewmodel =
                    ViewModelProvider(this@HomeFragment, viewModelFactory).get(EpisodeListViewModel::class.java)
                lifecycleOwner = viewLifecycleOwner
            }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
//        viewDataBinding.viewmodel?.channelListLive?.observe(viewLifecycleOwner, Observer {
//            updateUi(it)
//        })
        viewDataBinding.viewmodel?.episodeListLive?.observe(viewLifecycleOwner, Observer {
            updateUi(it)
        })
    }

//    private fun updateUi(channels: List<Episode>?) {
//        TODO("Not yet implemented")
//    }

    private fun updateUi(channels: List<Episode>?) {
        var msg = "Episode size: ${channels?.size ?: -2}"
        view?.findViewById<TextView>(R.id.text)?.text = msg
    }

    override fun onResume() {
        super.onResume()
        view?.findViewById<Button>(R.id.navigate_destination_button)?.setOnClickListener {
//            viewDataBinding.viewmodel?.fetchChannelList()
            viewDataBinding.viewmodel?.fetchEpisodeList()
        }
    }
}
