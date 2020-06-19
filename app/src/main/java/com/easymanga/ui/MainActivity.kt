package com.easymanga.ui

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.easymanga.BuildConfig
import com.easymanga.R
import com.easymanga.ui.base.BaseActivity
import com.easymanga.util.Constant
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var host: NavHostFragment
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        host = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment)
                as NavHostFragment? ?: return
        navController = host.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setSupportActionBar(toolbar)
        bottom_nav_view.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.manga_list_dest || destination.id == R.id.storage_dest) {
                bottom_nav_view.visibility = View.VISIBLE
            } else {
                bottom_nav_view.visibility = View.GONE
            }

            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                destination.id.toString()
            }
            if (BuildConfig.DEBUG) {
                Log.d(Constant.LOG_TAG, "Navigated to $dest")
            }
        }
    }
}
