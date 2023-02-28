package com.jymun.harusekki.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jymun.harusekki.R
import com.jymun.harusekki.data.model.cooking_step.CookingStep
import com.jymun.harusekki.data.model.ingredient.Ingredient
import com.jymun.harusekki.data.model.menu.MenuCategory
import com.jymun.harusekki.databinding.FragmentDetailBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import com.jymun.harusekki.ui.base.adapter.ModelRecyclerAdapter
import com.jymun.harusekki.ui.detail.cooking_step.CookingStepAdapterListener
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
            Log.d("# DetailFragment", it.exception.message)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNeedIngredientRecyclerView()
        initCookingStepRecyclerView()
        initAppbarLayout()
        initAddMenuButton()

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

    private fun initCookingStepRecyclerView() {
        binding.fragmentDetailContent.cookingStepRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = ModelRecyclerAdapter<CookingStep>(resourcesProvider).apply {
                addAdapterListener(cookingStepAdapterListener)
            }
        }
    }

    private val cookingStepAdapterListener = object : CookingStepAdapterListener {
        override fun onCookingStepImageClicked(imageUrl: String) = findNavController().navigate(
            DetailFragmentDirections.actionFragmentDetailToImageDetailFragment(imageUrl)
        )
    }

    private fun initAppbarLayout() {
        binding.appBarContainer.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            binding.toolbarLayout.alpha = -verticalOffset.toFloat() / appBarLayout.totalScrollRange
        }
    }

    private fun initAddMenuButton() {
        binding.addMenuButton.setOnClickListener {
            AddMenuDialog(requireActivity()) { year: Int, month: Int, dayOfMonth: Int, menuCategory: MenuCategory ->
                viewModel.addMenu(year, month, dayOfMonth, menuCategory)

                Snackbar.make(
                    binding.root,
                    "레시피를 ${year}년 ${month}월 ${dayOfMonth}일 ${
                        resourcesProvider.getString(
                            menuCategory.textResId
                        )
                    } 식단에 추가하였습니다.",
                    Snackbar.LENGTH_LONG
                ).apply {
                    setAction(resourcesProvider.getString(R.string.move)) {
                        moveToMenuFragment("$year-$month-$dayOfMonth")
                    }
                }.show()
            }.show()
        }
    }

    private fun moveToMenuFragment(dateStr: String) = findNavController().navigate(
        DetailFragmentDirections.actionFragmentDetailToFragmentMenu(dateStr)
    )
}