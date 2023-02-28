package com.jymun.harusekki.ui.ingredient

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.jymun.harusekki.databinding.FragmentIngredientBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IngredientFragment : BaseFragment<IngredientViewModel, FragmentIngredientBinding>() {

    override val viewModel: IngredientViewModel by viewModels()

    override fun getViewDataBinding() = FragmentIngredientBinding.inflate(layoutInflater)

    override fun setUpBinding() = binding.apply {
        viewModel = this@IngredientFragment.viewModel
        lifecycleOwner = viewLifecycleOwner
    }

    override fun observeState() = viewModel.loadState.observe(viewLifecycleOwner) {
        if (it is LoadState.Error)
            Toast.makeText(requireActivity(), it.exception.message, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
    }
}