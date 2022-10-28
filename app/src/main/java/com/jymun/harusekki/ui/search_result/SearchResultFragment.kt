package com.jymun.harusekki.ui.search_result

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jymun.harusekki.R
import com.jymun.harusekki.databinding.FragmentSearchResultBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import com.jymun.harusekki.ui.custom_view.OnSearchModeChangedListener
import com.jymun.harusekki.ui.home.recipe.RecipeSortOption
import com.jymun.harusekki.util.resources.ResourcesProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchResultFragment : BaseFragment<SearchResultViewModel, FragmentSearchResultBinding>() {

    @Inject
    lateinit var resourcesProvider: ResourcesProvider

    private val args by navArgs<SearchResultFragmentArgs>()

    override val viewModel: SearchResultViewModel by viewModels()

    override fun getViewDataBinding() = FragmentSearchResultBinding.inflate(layoutInflater)

    override fun setUpBinding() = binding.apply {
        viewModel = this@SearchResultFragment.viewModel
        lifecycleOwner = viewLifecycleOwner
    }

    override fun observeState() = viewModel.loadState.observe(viewLifecycleOwner) {
        if (it is LoadState.Error)
            Toast.makeText(requireActivity(), it.exception.message, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSortOptionSpinner()
        initByTitleModeButton()
        initSearchModeToggleGroup()

        viewModel.updateSearchMode(args.searchMode)
        viewModel.searchMode.observe(viewLifecycleOwner) {
            Log.d("# SearchResultFragment", "$it")
        }
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
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initByTitleModeButton() {
        val searchMode = args.searchMode
        binding.byTitleModeButton.apply {
            if (searchMode is SearchMode.ByTitle) {
                text =
                    "${resourcesProvider.getString(R.string.by_title_mode_on)} ${searchMode.keyword}"
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
                when (id) {
                    null -> Log.d("# SearchResultFragment", "ALL")

                    R.id.byTitleModeButton -> Log.d(
                        "# SearchResultFragment",
                        "title"
                    )

                    R.id.byIngredientModeButton -> Log.d(
                        "# SearchResultFragment",
                        "ingre"
                    )

                    R.id.favoriteModeButton -> Log.d(
                        "# SearchResultFragment",
                        "favorite"
                    )
                }
            }
        })
    }
}