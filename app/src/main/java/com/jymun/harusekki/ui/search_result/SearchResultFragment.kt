package com.jymun.harusekki.ui.search_result

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jymun.harusekki.R
import com.jymun.harusekki.databinding.FragmentSearchResultBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import com.jymun.harusekki.ui.home.recipe.RecipeSortOption
import com.jymun.harusekki.util.resources.ResourcesProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchResultFragment : BaseFragment<SearchResultViewModel, FragmentSearchResultBinding>() {

    @Inject
    lateinit var resourcesProvider: ResourcesProvider

    private val args by navArgs<SearchResultFragmentArgs>()
    private lateinit var searchMode: SearchMode

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

        searchMode = args.searchMode
        initSortOptionSpinner()
    }

    private fun initSortOptionSpinner() = binding.sortOptionSpinner.apply {
        dropDownWidth = resourcesProvider.getScreenWidth()
        dropDownVerticalOffset = 150
        adapter = SortOptionSpinnerAdapter(
            context = requireActivity(),
            resourcesProvider = resourcesProvider,
            layoutResId = R.layout.item_sort_option,
            values = RecipeSortOption.values(),
            baseSortOption = searchMode.sortOption
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
}