package com.jymun.harusekki.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.jymun.harusekki.R
import com.jymun.harusekki.data.model.recipe.RecipeCategory
import com.jymun.harusekki.databinding.FragmentHomeBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.adapter.ModelRecyclerAdapter
import com.jymun.harusekki.ui.extensions.showOtherPages
import com.jymun.harusekki.ui.home.recipe.category.RecipeCategoryGenerator
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

        initShortcuts()
        initRecipeCategoryRecyclerView()
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
            layoutManager = LinearLayoutManager(requireActivity(), HORIZONTAL, false)
            adapter = ModelRecyclerAdapter<RecipeCategory>(resourcesProvider).apply {
                submitList(RecipeCategoryGenerator.get())
            }
        }
    }
}