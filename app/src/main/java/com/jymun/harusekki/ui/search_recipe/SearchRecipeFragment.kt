package com.jymun.harusekki.ui.search_recipe

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.jymun.harusekki.R
import com.jymun.harusekki.data.model.recipe.Recipe
import com.jymun.harusekki.data.model.recipe.SearchKeyword
import com.jymun.harusekki.databinding.FragmentSearchRecipeBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import com.jymun.harusekki.ui.base.adapter.ModelRecyclerAdapter
import com.jymun.harusekki.ui.extensions.addSnapToStartHelper
import com.jymun.harusekki.ui.home.recipe.RecipeAdapterListener
import com.jymun.harusekki.ui.search_result.SearchMode
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
        initSearchEditText()
        initLatestReadRecipeRecyclerView()

        viewModel.loadAllSearchKeywords()
        viewModel.loadLatestReadRecipe()
    }

    private fun initSearchKeywordRecyclerView() {
        binding.latestSearchKeywordRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity(), HORIZONTAL, false)
            adapter = ModelRecyclerAdapter<SearchKeyword>(resourcesProvider).apply {
                addAdapterListener(object : SearchKeywordAdapterListener {
                    override fun onKeywordTextClicked(searchKeyword: SearchKeyword) {
                        viewModel.addSearchKeyword(searchKeyword)
                        moveToSearchResultFragment(searchKeyword.keyword)
                    }

                    override fun onDeleteButtonClicked(searchKeyword: SearchKeyword) {
                        viewModel.deleteSearchKeyword(searchKeyword)
                    }
                })
            }
        }
    }

    private fun moveToSearchResultFragment(keyword: String) = findNavController().navigate(
        SearchRecipeFragmentDirections.actionFragmentSearchRecipeToSearchResultFragment(
            SearchMode.ByTitle(keyword)
        )
    )

    private fun initSearchEditText() {
        binding.searchEditText.apply {
            requestFocus()
            setOnFocusChangeListener { _, isFocused ->
                binding.cancelButton.visibility = if (isFocused) View.VISIBLE else View.GONE
            }
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH && !text.isNullOrEmpty()) {
                    moveToSearchResultFragment(text.toString())
                    viewModel.addSearchKeyword(SearchKeyword(keyword = text.toString()))
                } else {
                    Toast.makeText(
                        requireActivity(),
                        resourcesProvider.getString(R.string.toast_empty_keyword),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                true
            }
        }
        showKeyboard()
    }

    private fun showKeyboard() {
        val imm: InputMethodManager = requireActivity().getSystemService(
            INPUT_METHOD_SERVICE
        ) as InputMethodManager
        imm.showSoftInput(binding.searchEditText, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun initLatestReadRecipeRecyclerView() {
        binding.latestReadRecipeRecyclerView.apply {
            addSnapToStartHelper()
            layoutManager = GridLayoutManager(requireActivity(), 1, HORIZONTAL, false)
            adapter = ModelRecyclerAdapter<Recipe>(resourcesProvider).apply {
                addAdapterListener(object : RecipeAdapterListener {
                    override fun onRecipeItemClicked(recipe: Recipe) {
                        viewModel.readRecipe(recipe)
                    }
                })
            }
        }
    }
}