package com.jymun.harusekki.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jymun.harusekki.databinding.FragmentDetailBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>() {

    private val args by navArgs<DetailFragmentArgs>()

    override val viewModel: DetailViewModel by viewModels()

    override fun getViewDataBinding() = FragmentDetailBinding.inflate(layoutInflater)

    override fun setUpBinding() = binding.apply {
        viewModel = this@DetailFragment.viewModel
        fragmentDetailContent.viewModel = this@DetailFragment.viewModel
        lifecycleOwner = viewLifecycleOwner
    }

    override fun observeState() = viewModel.loadState.observe(viewLifecycleOwner) {
        if (it is LoadState.Error) {
            Log.d("# DetailFragment", "${it.exception.message}")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("# DetailFragment", "onViewCreated() called")
        viewModel.loadData(args.id)
    }
}