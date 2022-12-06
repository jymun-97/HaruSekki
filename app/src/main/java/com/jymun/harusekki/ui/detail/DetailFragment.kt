package com.jymun.harusekki.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jymun.harusekki.databinding.FragmentDetailBinding
import com.jymun.harusekki.ui.base.BaseFragment

class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>() {

    private val args by navArgs<DetailFragmentArgs>()

    override val viewModel: DetailViewModel by viewModels()

    override fun getViewDataBinding() = FragmentDetailBinding.inflate(layoutInflater)

    override fun setUpBinding() = binding.apply {
        // TODO("Not yet implemented")
    }

    override fun observeState() {
        // TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}