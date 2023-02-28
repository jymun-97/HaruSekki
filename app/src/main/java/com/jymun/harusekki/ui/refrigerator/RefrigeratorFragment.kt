package com.jymun.harusekki.ui.refrigerator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jymun.harusekki.databinding.FragmentRefrigeratorBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RefrigeratorFragment : BaseFragment<RefrigeratorViewModel, FragmentRefrigeratorBinding>() {

    override val viewModel: RefrigeratorViewModel by viewModels()

    override fun getViewDataBinding() = FragmentRefrigeratorBinding.inflate(layoutInflater)

    override fun setUpBinding() = binding.apply {
        viewModel = this@RefrigeratorFragment.viewModel
        lifecycleOwner = viewLifecycleOwner
    }

    override fun observeState() = viewModel.loadState.observe(viewLifecycleOwner) {
        if (it is LoadState.Error)
            Toast.makeText(requireActivity(), it.exception.message, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAddIngredientButton()
    }

    private fun initAddIngredientButton() = binding.addIngredientButton.setOnClickListener {
        findNavController().navigate(
            RefrigeratorFragmentDirections.actionFragmentRefrigeratorToFragmentIngredient()
        )
    }
}