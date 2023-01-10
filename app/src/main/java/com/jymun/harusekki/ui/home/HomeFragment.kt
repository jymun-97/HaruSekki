package com.jymun.harusekki.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.jymun.harusekki.R
import com.jymun.harusekki.data.model.recipe.Recipe
import com.jymun.harusekki.data.model.recipe.RecipeCategory
import com.jymun.harusekki.databinding.FragmentHomeBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import com.jymun.harusekki.ui.base.adapter.ModelRecyclerAdapter
import com.jymun.harusekki.ui.extensions.addSnapToCenterHelper
import com.jymun.harusekki.ui.extensions.addSnapToStartHelper
import com.jymun.harusekki.ui.extensions.showOtherPages
import com.jymun.harusekki.ui.home.recipe.RecipeAdapterListener
import com.jymun.harusekki.ui.home.recipe.category.RecipeCategoryAdapterListener
import com.jymun.harusekki.ui.home.recipe.category.RecipeCategoryProvider
import com.jymun.harusekki.ui.home.shortcut.MemoShortcutFragment
import com.jymun.harusekki.ui.home.shortcut.MenuShortcutFragment
import com.jymun.harusekki.ui.home.shortcut.RefrigeratorShortcutFragment
import com.jymun.harusekki.ui.home.shortcut.ShortcutAdapter
import com.jymun.harusekki.ui.search_result.SearchMode
import com.jymun.harusekki.util.resources.ResourcesProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    @Inject
    lateinit var resourcesProvider: ResourcesProvider

    private val shortcutList = listOf(
        MenuShortcutFragment(),
        MemoShortcutFragment(),
        RefrigeratorShortcutFragment()
    )

    override val viewModel: HomeViewModel by viewModels()

    override fun getViewDataBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("# HomeFragment", "onViewCreated() called")

        initShortcuts()
        initRecipeCategoryRecyclerView()
        initBestRecipeRecyclerView()
        initLatestRecipeRecyclerview()
        initSearchImageButton()
        initSearchByIngredientButton()
        initSearchMoreBestRecipeButton()
        initSearchMoreLatestRecipeButton()

        viewModel.loadData()
    }

    override fun setUpBinding() = binding.apply {
        this.fragmentHomeContent.viewModel = this@HomeFragment.viewModel
        this.fragmentHomeContent.lifecycleOwner = viewLifecycleOwner
        viewModel = this@HomeFragment.viewModel
        lifecycleOwner = viewLifecycleOwner
    }

    override fun observeState() = viewModel.loadState.observe(viewLifecycleOwner) {
        if (it is LoadState.Error)
            Toast.makeText(requireActivity(), it.exception.message, Toast.LENGTH_SHORT).show()
    }

    private fun initShortcuts() = with(resourcesProvider) {
        binding.fragmentHomeContent.shortcutViewPager.apply {
            adapter = ShortcutAdapter(
                parent = this@HomeFragment,
                fragments = shortcutList
            )
            showOtherPages(
                numberOfPages = 3,
                pageMargin = getDimension(R.dimen.shortcut_page_margin),
                pageWidth = getDimension(R.dimen.shortcut_page_width),
                screenWidth = getScreenWidth()
            )
        }
    }

    private fun initRecipeCategoryRecyclerView() {
        binding.fragmentHomeContent.recipeCategoryRecyclerView.apply {
            addSnapToCenterHelper()
            layoutManager = LinearLayoutManager(requireActivity(), HORIZONTAL, false)
            adapter = ModelRecyclerAdapter<RecipeCategory>(resourcesProvider).apply {
                submitList(RecipeCategoryProvider.get(resourcesProvider))

                addAdapterListener(object : RecipeCategoryAdapterListener {
                    override fun onRecipeCategoryItemClicked(recipeCategory: RecipeCategory) {
                        moveToSearchResultFragment(SearchMode.ByCategory(recipeCategory.name))
                    }
                })
            }
        }
    }

    private val recipeAdapterListener = object : RecipeAdapterListener {
        override fun onRecipeItemClicked(recipe: Recipe) = moveToDetailFragment(recipe.id)
    }

    private fun initBestRecipeRecyclerView() {
        binding.fragmentHomeContent.bestRecipeRecyclerView.apply {
            addSnapToStartHelper()
            layoutManager = GridLayoutManager(requireActivity(), 1, HORIZONTAL, false)
            adapter = ModelRecyclerAdapter<Recipe>(resourcesProvider).apply {
                addAdapterListener(recipeAdapterListener)
            }
        }
    }

    private fun initLatestRecipeRecyclerview() {
        binding.fragmentHomeContent.latestRecipeRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = ModelRecyclerAdapter<Recipe>(resourcesProvider).apply {
                addAdapterListener(recipeAdapterListener)
            }
        }
    }

    private fun initSearchImageButton() {
        binding.searchImageButton.setOnClickListener { moveToSearchRecipeFragment() }
        binding.searchTextButton.setOnClickListener { moveToSearchRecipeFragment() }
    }

    private fun initSearchByIngredientButton() {
        binding.searchRecipeByIngredientsButton.setOnClickListener {
            moveToSearchResultFragment(SearchMode.ByIngredient)
        }
    }

    private fun initSearchMoreBestRecipeButton() {
        binding.fragmentHomeContent.searchMoreBestRecipe.setOnClickListener {
            moveToSearchResultFragment(SearchMode.Best)
        }
    }

    private fun initSearchMoreLatestRecipeButton() {
        binding.fragmentHomeContent.searchMoreLatestRecipe.setOnClickListener {
            moveToSearchResultFragment(SearchMode.Latest)
        }
    }

    private fun moveToDetailFragment(id: Long) = findNavController().navigate(
        HomeFragmentDirections.actionFragmentHomeToDetailFragment(id)
    )

    private fun moveToSearchRecipeFragment() = findNavController().navigate(
        HomeFragmentDirections.actionFragmentHomeToSearchRecipeFragment()
    )

    private fun moveToSearchResultFragment(searchMode: SearchMode) = findNavController().navigate(
        HomeFragmentDirections.actionFragmentHomeToFragmentSearchResult(searchMode)
    )
}