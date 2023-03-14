package com.jymun.harusekki.ui.home.shortcut

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.jymun.harusekki.R
import com.jymun.harusekki.data.model.menu.Menu
import com.jymun.harusekki.data.model.menu.MenuCategory
import com.jymun.harusekki.databinding.ShortcutMenuBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.adapter.ModelRecyclerAdapter
import com.jymun.harusekki.ui.home.HomeFragmentDirections
import com.jymun.harusekki.ui.menu.MenuPageViewModel
import com.jymun.harusekki.util.resources.ResourcesProvider
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@AndroidEntryPoint
class MenuShortcutFragment : BaseFragment<MenuPageViewModel, ShortcutMenuBinding>() {

    @Inject
    lateinit var resourcesProvider: ResourcesProvider
    private lateinit var menuCategory: MenuCategory
    private lateinit var curDate: LocalDate
    private lateinit var menuAdapter: ModelRecyclerAdapter<Menu>

    override val viewModel: MenuPageViewModel by viewModels()

    override fun getViewDataBinding() = ShortcutMenuBinding.inflate(layoutInflater)

    override fun setUpBinding() = binding.apply {
        viewModel = this@MenuShortcutFragment.viewModel
        lifecycleOwner = viewLifecycleOwner
    }

    override fun observeState() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMenuCategory()
        initMenuTitleTextView()
        initTodayTextView()
        initMenuRecyclerView()
        initMoveToMenuFragmentButton()

        viewModel.loadMenu(
            year = curDate.year,
            month = curDate.monthValue,
            dayOfMonth = curDate.dayOfMonth
        )
        when (menuCategory) {
            MenuCategory.BREAKFAST -> viewModel.breakfast.observe(viewLifecycleOwner) {
                menuAdapter.submitList(it)
                binding.emptyMenuTextView.visibility =
                    if (it.isNullOrEmpty()) View.VISIBLE else View.GONE
            }
            MenuCategory.LUNCH -> viewModel.lunch.observe(viewLifecycleOwner) {
                menuAdapter.submitList(it)
                binding.emptyMenuTextView.visibility =
                    if (it.isNullOrEmpty()) View.VISIBLE else View.GONE
            }
            MenuCategory.DINNER -> viewModel.dinner.observe(viewLifecycleOwner) {
                menuAdapter.submitList(it)
                binding.emptyMenuTextView.visibility =
                    if (it.isNullOrEmpty()) View.VISIBLE else View.GONE
            }
        }
    }

    private fun initMenuCategory() {
        menuCategory = when (LocalDateTime.now().hour) {
            in 0..8 -> MenuCategory.BREAKFAST

            in 9..14 -> MenuCategory.LUNCH

            else -> MenuCategory.DINNER
        }
    }

    private fun initMenuTitleTextView() = binding.shortcutTitleTextView.apply {
        text = "오늘의 ${resourcesProvider.getString(menuCategory.textResId)} 메뉴"
    }

    private fun initTodayTextView() = binding.todayTextView.apply {
        curDate = LocalDate.now()

        val month = curDate.monthValue
        val day = curDate.dayOfMonth
        val dayOfWeek =
            resourcesProvider.getStringArray(R.array.dayOfWeek)[curDate.dayOfWeek.ordinal]

        text = "${month}월 ${day}일 (${dayOfWeek})"
    }

    private fun initMenuRecyclerView() = binding.menuRecyclerView.apply {
        layoutManager = GridLayoutManager(requireActivity(), 3, VERTICAL, false)
        adapter = ModelRecyclerAdapter<Menu>(resourcesProvider).also {
            menuAdapter = it
        }
    }

    private fun initMoveToMenuFragmentButton() = binding.moveToMenuFragmentButton.apply {
        setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionFragmentHomeToFragmentMenu()
            )
        }
    }
}