package com.jymun.harusekki.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.jymun.harusekki.databinding.ActivityMainBinding
import com.jymun.harusekki.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel by viewModels<MainViewModel>()

    override fun getViewDataBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
    }
}