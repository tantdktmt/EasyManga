package com.easymanga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.easymanga.core.BaseFragment

class SplashFragment : BaseFragment() {

    companion object {
        private val SPLASH_DURATION = 1000L
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed({
            navController.navigate(R.id.next_action)
        }, SPLASH_DURATION)
    }
}
