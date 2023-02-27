package com.jymun.harusekki.ui.menu

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jymun.harusekki.data.model.menu.Menu
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
    private val date: LocalDate
) : BaseFragment<MenuViewModel, FragmentMenuPageBinding>() {

    @Inject
    lateinit var resourcesProvider: ResourcesProvider

    override val viewModel: MenuViewModel by viewModels()

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

        override fun onDeleteMenuButtonClicked(menu: Menu) {
            //todo menu delete
        }

        override fun onMenuItemClicked(recipeId: Long) {
            findNavController().navigate(
                MenuFragmentDirections.actionFragmentMenuToFragmentDetail(recipeId)
            )
        }
    }
}