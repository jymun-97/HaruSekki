package com.jymun.harusekki.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.jymun.harusekki.R
import com.jymun.harusekki.databinding.FragmentHomeBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.extensions.showOtherPages
import com.jymun.harusekki.ui.shortcut.MemoShortcutFragment
import com.jymun.harusekki.ui.shortcut.MenuShortcutFragment
import com.jymun.harusekki.ui.shortcut.RefrigeratorShortcutFragment
import com.jymun.harusekki.ui.shortcut.ShortcutAdapter
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
}