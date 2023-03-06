package com.jymun.harusekki.ui.refrigerator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jymun.harusekki.data.model.ingredient.IngredientByCategory
import com.jymun.harusekki.databinding.FragmentRefrigeratorBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import com.jymun.harusekki.ui.base.adapter.ModelRecyclerAdapter
import com.jymun.harusekki.ui.ingredient.IngredientManager
import com.jymun.harusekki.util.resources.ResourcesProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RefrigeratorFragment : BaseFragment<RefrigeratorViewModel, FragmentRefrigeratorBinding>() {

    @Inject
    lateinit var resourcesProvider: ResourcesProvider
    private lateinit var ingredientByCategoryAdapter: ModelRecyclerAdapter<IngredientByCategory>

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
        initAllIngredientRecyclerView()
        initDeleteIngredientButton()

        viewModel.loadIngredients()
        viewModel.ingredientByCategoryList.observe(viewLifecycleOwner) {
            ingredientByCategoryAdapter.submitList(it)
        }
    }

    private fun initAddIngredientButton() = binding.addIngredientButton.setOnClickListener {
        findNavController().navigate(
            RefrigeratorFragmentDirections.actionFragmentRefrigeratorToFragmentIngredient()
        )
    }

    private fun initAllIngredientRecyclerView() {
        ingredientByCategoryAdapter = ModelRecyclerAdapter(resourcesProvider)
        binding.allIngredientRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = ingredientByCategoryAdapter
        }
    }

    private fun initDeleteIngredientButton() {
        binding.deleteIngredientButton.setOnClickListener {
            viewModel.deleteSelectedIngredients() {
                IngredientManager.clear()
                viewModel.loadIngredients()
            }
        }
    }
}