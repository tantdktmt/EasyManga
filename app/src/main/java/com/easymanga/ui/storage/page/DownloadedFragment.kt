package com.easymanga.ui.storage.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.easymanga.databinding.FragmentDownloadedBinding
import com.easymanga.ui.base.BaseFragment

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
    }
}
