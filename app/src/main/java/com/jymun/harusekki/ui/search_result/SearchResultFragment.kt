package com.jymun.harusekki.ui.search_result

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jymun.harusekki.databinding.FragmentSearchResultBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultFragment : BaseFragment<SearchResultViewModel, FragmentSearchResultBinding>() {

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

        val searchMode = args.searchMode
        Toast.makeText(
            requireActivity(),
            when (searchMode) {
                is SearchMode.ByTitle -> searchMode.keyword

                is SearchMode.ByCategory -> searchMode.category

                is SearchMode.Best -> "Best"

                is SearchMode.Favorite -> "Favorite"

                is SearchMode.ByIngredient -> "Ingredient"

                is SearchMode.Latest -> "Latest"
            },
            Toast.LENGTH_SHORT
        ).show()
    }

}