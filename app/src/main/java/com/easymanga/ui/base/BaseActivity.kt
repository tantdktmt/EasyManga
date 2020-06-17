package com.easymanga.ui.base

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.easymanga.EasyMangaApplication
import com.easymanga.R
import com.easymanga.ui.SharedViewModel
import com.easymanga.util.DialogUtil
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val viewModel: SharedViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EasyMangaApplication.getInstance().getAppComponent().inject(this)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.loadingState?.observe(this, Observer {
            if (it == BaseViewModel.LoadingState.FAILED) {
                DialogUtil.showMessageDialog(this, R.string.alert_error_message)
            }
        })
    }
}