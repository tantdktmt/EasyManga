package com.easymanga.ui.mangalist

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
import androidx.recyclerview.widget.RecyclerView
import com.easymanga.EasyMangaApplication
import com.easymanga.R
import com.easymanga.data.Manga
import com.easymanga.databinding.FragmentMangaListBinding
import com.easymanga.ui.base.BaseFragment
import com.easymanga.util.Constant
import javax.inject.Inject

class MangaListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: FragmentMangaListBinding

    private val mangas = ArrayList<Manga>()
    private lateinit var adapter: MangaListAdapter
    private lateinit var rvManga: RecyclerView

    override fun onAttach(context: Context) {
        EasyMangaApplication.getInstance().getAppComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentMangaListBinding.inflate(inflater, container, false)
            .apply {
                viewmodel =
                    ViewModelProvider(this@MangaListFragment, viewModelFactory).get(
                        MangaListViewModel::class.java
                    )
                lifecycleOwner = viewLifecycleOwner
            }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MangaListAdapter(mangas)
        rvManga = view.findViewById(R.id.rv_manga)
        rvManga.setHasFixedSize(true)
        rvManga.layoutManager = GridLayoutManager(context, 2)
        rvManga.itemAnimator = DefaultItemAnimator()
        rvManga.adapter = adapter
        setupObservers()
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.mangaListLive?.observe(viewLifecycleOwner, Observer {
            if (Constant.IS_DEBUG_MODE) {
                Log.d(Constant.LOG_TAG, "Manga list: $it")
            }
            mangas.addAll(it)
            // TESTING
            val manga = it[0]
            for(i in 0..9) {
                mangas.add(manga)
            }
            // TESTING
            adapter.notifyDataSetChanged()
        })
    }

    override fun onResume() {
        super.onResume()
        viewDataBinding.viewmodel?.fetchMangaList()
    }
}
