package com.jymun.harusekki.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.jymun.harusekki.R
import com.jymun.harusekki.databinding.ActivityMainBinding
import com.jymun.harusekki.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel by viewModels<MainViewModel>()

    override fun getViewDataBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNav()
    }

    private fun initBottomNav() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController.apply {
            addOnDestinationChangedListener { _, destination, _ ->
                binding.bottomNavigationView.visibility = when (destination.id) {
                    R.id.fragment_detail -> View.GONE

                    else -> View.VISIBLE
                }
            }
        }

        binding.bottomNavigationView.setupWithNavController(navController)
    }
}