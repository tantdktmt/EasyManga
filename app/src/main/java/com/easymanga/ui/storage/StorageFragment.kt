package com.easymanga.ui.storage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.easymanga.EasyMangaApplication
import com.easymanga.databinding.FragmentStorageBinding
import com.easymanga.ui.base.BaseFragment

class StorageFragment : BaseFragment() {

    private lateinit var viewDataBinding: FragmentStorageBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        EasyMangaApplication.getInstance().getAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentStorageBinding.inflate(inflater, container, false)
            .apply {
                viewmodel = viewModel
                lifecycleOwner = viewLifecycleOwner
            }
        return viewDataBinding.root
    }
}
