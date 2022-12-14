package com.jymun.harusekki.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.jymun.harusekki.R
import com.jymun.harusekki.data.model.ingredient.Ingredient
import com.jymun.harusekki.databinding.FragmentDetailBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import com.jymun.harusekki.ui.base.adapter.ModelRecyclerAdapter
import com.jymun.harusekki.util.resources.ResourcesProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>() {

    @Inject
    lateinit var resourcesProvider: ResourcesProvider

    private val args by navArgs<DetailFragmentArgs>()

    override val viewModel: DetailViewModel by viewModels()

    override fun getViewDataBinding() = FragmentDetailBinding.inflate(layoutInflater)

    override fun setUpBinding() = binding.apply {
        viewModel = this@DetailFragment.viewModel
        fragmentDetailContent.viewModel = this@DetailFragment.viewModel
        lifecycleOwner = viewLifecycleOwner
    }

    override fun observeState() = viewModel.loadState.observe(viewLifecycleOwner) {
        if (it is LoadState.Error) {
            Log.d("# DetailFragment", "${it.exception.message}")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNeedIngredientRecyclerView()

        viewModel.loadData(args.id)
    }

    private fun initNeedIngredientRecyclerView() {
        binding.fragmentDetailContent.needIngredientRecyclerView.apply {
            adapter = ModelRecyclerAdapter<Ingredient>(resourcesProvider)
            layoutManager = object : GridLayoutManager(
                requireActivity(),
                resourcesProvider.getInteger(R.integer.count_showing_ingredient_items),
                VERTICAL,
                false
            ) {
                override fun canScrollVertically(): Boolean = false
            }
        }
    }
}