package com.easymanga.ui.mangalist

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
import com.easymanga.data.Manga
import com.easymanga.databinding.FragmentMangaListBinding
import com.easymanga.ui.base.BaseFragment
import com.easymanga.util.CommonUtil
import com.easymanga.util.Constant
import kotlinx.android.synthetic.main.fragment_manga_list.*

class MangaListFragment : BaseFragment() {

    private lateinit var viewDataBinding: FragmentMangaListBinding

    private val mangas = ArrayList<Manga>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        EasyMangaApplication.getInstance().getAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentMangaListBinding.inflate(inflater, container, false)
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
        rv_manga.apply {
            layoutManager = GridLayoutManager(context, 2)
            itemAnimator = DefaultItemAnimator()
            this.adapter = MangaListAdapter(mangas)
        }
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.mangaList?.observe(viewLifecycleOwner, Observer {
            if (BuildConfig.DEBUG) {
                Log.d(Constant.LOG_TAG, "Manga list: $it")
            }
            mangas.clear()
            mangas.addAll(it)
            rv_manga.adapter?.notifyDataSetChanged()
        })
    }

    override fun onResume() {
        super.onResume()
        if (CommonUtil.isListEmpty(viewDataBinding.viewmodel?.mangaList?.value)) {
            viewDataBinding.viewmodel?.fetchMangaList()
        }
    }
}
