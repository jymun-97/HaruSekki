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

        viewModel.loadData()
    }

    override fun setUpBinding() = binding.apply {
        this.fragmentHomeContent.viewModel = this@HomeFragment.viewModel
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
                submitList(RecipeCategoryProvider(resourcesProvider).get())

                addAdapterListener(object : RecipeCategoryAdapterListener {
                    override fun onRecipeCategoryItemClicked(recipeCategory: RecipeCategory) {
                        // TODO. 레시피 카테고리 아이템 클릭 콜백
                        Toast.makeText(
                            requireActivity(),
                            recipeCategory.name,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
        }
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

    private val recipeAdapterListener = object : RecipeAdapterListener {
        override fun onRecipeItemClicked(recipe: Recipe) = findNavController().navigate(
            HomeFragmentDirections.actionFragmentHomeToDetailFragment(recipe.id)
        )
    }
}