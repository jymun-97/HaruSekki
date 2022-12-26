package com.jymun.harusekki.ui.search_recipe

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.jymun.harusekki.databinding.FragmentSearchRecipeBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchRecipeFragment : BaseFragment<SearchRecipeViewModel, FragmentSearchRecipeBinding>() {

    override val viewModel: SearchRecipeViewModel by viewModels()

    override fun getViewDataBinding() = FragmentSearchRecipeBinding.inflate(layoutInflater)

    override fun setUpBinding() = binding.apply {
        this.fragmentSearchRecipeContent.viewModel = this@SearchRecipeFragment.viewModel
        this.fragmentSearchRecipeContent.lifecycleOwner = viewLifecycleOwner
        viewModel = this@SearchRecipeFragment.viewModel
        lifecycleOwner = viewLifecycleOwner
    }

    override fun observeState() = viewModel.loadState.observe(viewLifecycleOwner) {
        if (it is LoadState.Error)
            Toast.makeText(requireActivity(), it.exception.message, Toast.LENGTH_SHORT).show()
    }
}