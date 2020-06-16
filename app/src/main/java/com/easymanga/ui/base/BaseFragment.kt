package com.easymanga.ui.base

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.easymanga.EasyMangaApplication
import com.easymanga.R
import com.easymanga.ui.SharedViewModel
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var navController: NavController
    protected lateinit var handler: Handler
    protected lateinit var viewModel: SharedViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        EasyMangaApplication.getInstance().getAppComponent().inject(this)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(SharedViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        handler = Handler()
    }

    protected fun navigate(restId: Int) {
        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }
        navController.navigate(restId, null, options)
    }
}