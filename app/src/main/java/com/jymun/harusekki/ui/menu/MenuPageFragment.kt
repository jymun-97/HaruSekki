package com.jymun.harusekki.ui.menu

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jymun.harusekki.R
import com.jymun.harusekki.data.model.menu.Menu
import com.jymun.harusekki.data.model.menu.MenuCategory
import com.jymun.harusekki.databinding.FragmentMenuPageBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.base.LoadState
import com.jymun.harusekki.ui.base.adapter.ModelRecyclerAdapter
import com.jymun.harusekki.util.resources.ResourcesProvider
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class MenuPageFragment(
    private val date: LocalDate,
    private val onMenuAdded: () -> Unit
) : BaseFragment<MenuPageViewModel, FragmentMenuPageBinding>() {

    @Inject
    lateinit var resourcesProvider: ResourcesProvider

    override val viewModel: MenuPageViewModel by viewModels()

    override fun getViewDataBinding() = FragmentMenuPageBinding.inflate(layoutInflater)

    override fun setUpBinding() = binding.apply {
        viewModel = this@MenuPageFragment.viewModel
        lifecycleOwner = viewLifecycleOwner
    }

    override fun observeState() = viewModel.loadState.observe(viewLifecycleOwner) {
        if (it is LoadState.Error)
            Toast.makeText(requireActivity(), it.exception.message, Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMenuRecyclerView()
        initAddMenuButtons()

        viewModel.loadMenu(date.year, date.monthValue, date.dayOfMonth)
    }

    private fun initMenuRecyclerView() = binding.apply {
        breakfastRecyclerView.adapter = ModelRecyclerAdapter<Menu>(resourcesProvider).apply {
            addAdapterListener(menuAdapterListener)
        }
        lunchRecyclerView.adapter = ModelRecyclerAdapter<Menu>(resourcesProvider).apply {
            addAdapterListener(menuAdapterListener)
        }
        dinnerRecyclerView.adapter = ModelRecyclerAdapter<Menu>(resourcesProvider).apply {
            addAdapterListener(menuAdapterListener)
        }
    }

    private val menuAdapterListener = object : MenuAdapterListener {

        override fun onMenuItemClicked(recipeId: Long) {
            findNavController().navigate(
                MenuFragmentDirections.actionFragmentMenuToFragmentDetail(recipeId)
            )
        }
    }

    private fun initAddMenuButtons() = binding.apply {
        addBreakfastMenuButton.addOnEnterClicked(onEmptyText) {
            addMenu(it, MenuCategory.BREAKFAST)
        }
        addLunchMenuButton.addOnEnterClicked(onEmptyText) {
            addMenu(it, MenuCategory.LUNCH)
        }
        addDinnerMenuButton.addOnEnterClicked(onEmptyText) {
            addMenu(it, MenuCategory.DINNER)
        }
    }

    private fun addMenu(menuTitle: String, category: MenuCategory) {
        onMenuAdded()
        viewModel.addMenu(
            Menu(
                year = date.year,
                month = date.monthValue,
                dayOfMonth = date.dayOfMonth,
                category = category,
                menuTitle = menuTitle,
                recipeId = null
            ), date.year, date.monthValue, date.dayOfMonth
        )
    }

    private val onEmptyText: () -> Unit = {
        Toast.makeText(
            requireActivity(),
            resourcesProvider.getString(R.string.toast_empty_menu),
            Toast.LENGTH_SHORT
        ).show()
    }

    fun deleteMenu() {
        viewModel.deleteMenu(date.year, date.monthValue, date.dayOfMonth)
    }

    fun pasteMenu(copiedDate: LocalDate) {
        viewModel.pasteMenu(
            copiedDate.year,
            copiedDate.monthValue,
            copiedDate.dayOfMonth,
            date.year,
            date.monthValue,
            date.dayOfMonth
        )
    }
}