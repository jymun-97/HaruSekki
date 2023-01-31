package com.jymun.harusekki.ui.search_result

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jymun.harusekki.data.model.recipe.Recipe
import com.jymun.harusekki.databinding.FragmentSearchResultPageBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import com.jymun.harusekki.ui.base.adapter.ModelRecyclerAdapter
import com.jymun.harusekki.ui.home.recipe.category.RecipeCategory
import com.jymun.harusekki.util.resources.ResourcesProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchResultPage(
    private val category: RecipeCategory
) : BaseFragment<SearchResultViewModel, FragmentSearchResultPageBinding>() {

    @Inject
    lateinit var resourcesProvider: ResourcesProvider

    override val viewModel: SearchResultViewModel by activityViewModels()

    override fun getViewDataBinding() = FragmentSearchResultPageBinding.inflate(layoutInflater)

    override fun setUpBinding() = binding.apply {
        viewModel = this@SearchResultPage.viewModel
        lifecycleOwner = viewLifecycleOwner
    }

    override fun observeState() = viewModel.loadState.observe(viewLifecycleOwner) {
        if (it is LoadState.Error)
            Toast.makeText(requireActivity(), it.exception.message, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchResultRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateCategory(category)
    }

    private fun initSearchResultRecyclerView() {
        binding.searchResultRecyclerView.apply {
            adapter = ModelRecyclerAdapter<Recipe>(resourcesProvider)
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }
}