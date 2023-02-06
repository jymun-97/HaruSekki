package com.jymun.harusekki.ui.search_result

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.jymun.harusekki.R
import com.jymun.harusekki.databinding.FragmentSearchResultBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import com.jymun.harusekki.ui.custom_view.OnSearchModeChangedListener
import com.jymun.harusekki.ui.home.recipe.RecipeSortOption
import com.jymun.harusekki.ui.home.recipe.category.RecipeCategory
import com.jymun.harusekki.util.resources.ResourcesProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchResultFragment : BaseFragment<SearchResultViewModel, FragmentSearchResultBinding>() {

    @Inject
    lateinit var resourcesProvider: ResourcesProvider

    private val args by navArgs<SearchResultFragmentArgs>()
    private var searchKeyword: String? = null
    private lateinit var searchMode: SearchMode
    private val categoryList = RecipeCategory.values().toList()

    override val viewModel: SearchResultViewModel by activityViewModels()

    override fun getViewDataBinding() = FragmentSearchResultBinding.inflate(layoutInflater)

    override fun setUpBinding() = binding.apply {
        viewModel = this@SearchResultFragment.viewModel
        lifecycleOwner = viewLifecycleOwner
        searchResultFragmentContent.lifecycleOwner = viewLifecycleOwner
    }

    override fun observeState() = viewModel.loadState.observe(viewLifecycleOwner) {
        if (it is LoadState.Error)
            Snackbar.make(binding.root, it.exception.message, Snackbar.LENGTH_LONG).apply {
                setAction(resourcesProvider.getString(R.string.go_back)) {
                    findNavController().popBackStack()
                    dismiss()
                }
            }.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchMode = args.searchMode

        initSortOptionSpinner()
        initByTitleModeButton()
        initSearchModeToggleGroup()
        initSearchResultPager()
        initCategoryTabLayout()
        setInitialPage()

        viewModel.updateSearchMode(searchMode)
    }

    private fun initSortOptionSpinner() = binding.sortOptionSpinner.apply {
        dropDownWidth = resourcesProvider.getScreenWidth()
        dropDownVerticalOffset = 150
        adapter = SortOptionSpinnerAdapter(
            context = requireActivity(),
            resourcesProvider = resourcesProvider,
            layoutResId = R.layout.item_sort_option,
            values = RecipeSortOption.values(),
            baseSortOption = args.searchMode.sortOption
        )
        viewTreeObserver.addOnWindowFocusChangeListener { isClosed ->
            background = resourcesProvider.getDrawable(
                if (isClosed) R.drawable.background_spinner_closed
                else R.drawable.background_spinner_opened
            )
        }
        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                (adapter as SortOptionSpinnerAdapter).updateTarget(position)
                viewModel.updateSortOption(RecipeSortOption.values()[position])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initByTitleModeButton() {
        binding.byTitleModeButton.apply {
            if (searchMode is SearchMode.ByTitle) {
                searchKeyword = (searchMode as SearchMode.ByTitle).keyword
                text = "${resourcesProvider.getString(R.string.by_title_mode_on)} $searchKeyword"

                performClick()
            } else {
                text = resourcesProvider.getString(R.string.by_title_mode_off)
                setOnClickListener {
                    moveToSearchRecipeFragment()
                }
            }
        }
    }

    private fun moveToSearchRecipeFragment() = findNavController().navigate(
        SearchResultFragmentDirections.actionFragmentSearchResultToFragmentSearchRecipe()
    )

    private fun initSearchModeToggleGroup() {
        binding.searchModeToggleGroup.setOnSearchModeChangedListener(object :
            OnSearchModeChangedListener {
            override fun onSearchModeChanged(id: Int?) {
                searchMode = when (id) {
                    R.id.byTitleModeButton -> searchKeyword?.let {
                        SearchMode.ByTitle(it, searchMode.sortOption)
                    } ?: return

                    R.id.byIngredientModeButton -> SearchMode.ByIngredient(searchMode.sortOption)

                    R.id.favoriteModeButton -> SearchMode.Favorite(searchMode.sortOption)

                    else -> SearchMode.All(searchMode.sortOption)
                }
                viewModel.updateSearchMode(searchMode)
            }
        })
    }

    private fun initSearchResultPager() {
        binding.searchResultFragmentContent.searchResultPager.adapter = SearchResultPageAdapter(
            fragmentActivity = requireActivity(),
            fragmentList = categoryList.map { SearchResultPage(it) }
        )
    }

    private fun initCategoryTabLayout() = with(binding) {
        TabLayoutMediator(
            categoryTabLayout,
            binding.searchResultFragmentContent.searchResultPager
        ) { tab, pos ->
            val target = categoryList[pos]

            tab.text = resourcesProvider.getString(target.textStrId)
        }.attach()
    }

    private fun setInitialPage() {
        val target = RecipeCategory.values().indexOf(searchMode.category)

        binding.categoryTabLayout.getTabAt(target)?.select()
        binding.searchResultFragmentContent.searchResultPager.setCurrentItem(target, false)
    }
}