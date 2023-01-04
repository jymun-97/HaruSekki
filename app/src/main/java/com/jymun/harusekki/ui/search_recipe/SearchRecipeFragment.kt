package com.jymun.harusekki.ui.search_recipe

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.jymun.harusekki.data.model.recipe.SearchKeyword
import com.jymun.harusekki.databinding.FragmentSearchRecipeBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import com.jymun.harusekki.ui.base.adapter.ModelRecyclerAdapter
import com.jymun.harusekki.util.resources.ResourcesProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchRecipeFragment : BaseFragment<SearchRecipeViewModel, FragmentSearchRecipeBinding>() {

    @Inject
    lateinit var resourcesProvider: ResourcesProvider

    override val viewModel: SearchRecipeViewModel by viewModels()

    override fun getViewDataBinding() = FragmentSearchRecipeBinding.inflate(layoutInflater)

    override fun setUpBinding() = binding.apply {
        viewModel = this@SearchRecipeFragment.viewModel
        lifecycleOwner = viewLifecycleOwner
    }

    override fun observeState() = viewModel.loadState.observe(viewLifecycleOwner) {
        if (it is LoadState.Error)
            Toast.makeText(requireActivity(), it.exception.message, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchKeywordRecyclerView()
//        createSearchKeywordForTest()
        viewModel.loadAllSearchKeywords()
    }

    private fun initSearchKeywordRecyclerView() {
        binding.latestSearchKeywordRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity(), HORIZONTAL, false)
            adapter = ModelRecyclerAdapter<SearchKeyword>(resourcesProvider).apply {
                addAdapterListener(object : SearchKeywordAdapterListener {
                    override fun onKeywordTextClicked(keyword: String) {
                        moveToSearchResultFragment()
                    }

                    override fun onDeleteButtonClicked(searchKeyword: SearchKeyword) {
                        viewModel.deleteSearchKeyword(searchKeyword)
                    }
                })
            }
        }
    }

    private fun moveToSearchResultFragment() = findNavController().navigate(
        SearchRecipeFragmentDirections.actionFragmentSearchRecipeToSearchResultFragment()
    )

    private fun createSearchKeywordForTest() {
        val testData = listOf(
            SearchKeyword(keyword = "비빔밥"),
            SearchKeyword(keyword = "김치찌개"),
            SearchKeyword(keyword = "삼겹살"),
            SearchKeyword(keyword = "제육볶음"),
            SearchKeyword(keyword = "국수"),
            SearchKeyword(keyword = "라면"),
            SearchKeyword(keyword = "된장찌개"),
            SearchKeyword(keyword = "김치볶음밥"),
            SearchKeyword(keyword = "시금치"),
            SearchKeyword(keyword = "나물"),
        )
        testData.forEach { viewModel.addSearchKeyword(it) }
    }
}