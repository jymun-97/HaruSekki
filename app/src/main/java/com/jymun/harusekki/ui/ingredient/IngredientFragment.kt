package com.jymun.harusekki.ui.ingredient

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jymun.harusekki.data.model.ingredient.IngredientByCategory
import com.jymun.harusekki.databinding.FragmentIngredientBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import com.jymun.harusekki.ui.base.adapter.ModelRecyclerAdapter
import com.jymun.harusekki.util.resources.ResourcesProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IngredientFragment : BaseFragment<IngredientViewModel, FragmentIngredientBinding>() {

    @Inject
    lateinit var resourcesProvider: ResourcesProvider

    private lateinit var ingredientByCategoryAdapter: ModelRecyclerAdapter<IngredientByCategory>

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

        initAllIngredientRecyclerView()
        initAddInRefrigeratorButton()

        viewModel.searchKeyword.postValue("")
        viewModel.ingredientByCategoryList.observe(viewLifecycleOwner) {
            ingredientByCategoryAdapter.submitList(it)
        }
    }

    private fun initAllIngredientRecyclerView() {
        ingredientByCategoryAdapter = ModelRecyclerAdapter(resourcesProvider)
        binding.fragmentIngredientContent.allIngredientRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = ingredientByCategoryAdapter
        }
    }

    private fun initAddInRefrigeratorButton() {
        binding.addInRefrigeratorButton.setOnClickListener {
            viewModel.addSelectedIngredients() {
                IngredientManager.clear()
                findNavController().popBackStack()
            }
        }
    }
}