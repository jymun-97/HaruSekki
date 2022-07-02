package com.harusekki.jymun.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.harusekki.jymun.databinding.FragmentHomeBinding
import com.harusekki.jymun.ui.adapter.CategoryAdapter
import com.harusekki.jymun.ui.adapter.ShortcutAdapter
import com.harusekki.jymun.util.Destination.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCategoryRecyclerView()
        initShortcutRecyclerView()
    }

    private fun initCategoryRecyclerView() {
        binding.categoryRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity(), HORIZONTAL, false)
            adapter = CategoryAdapter()
        }
    }

    private fun initShortcutRecyclerView() {
        binding.shortcutRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity(), HORIZONTAL, false)
            adapter = ShortcutAdapter(
                shortcutGoButtonClicked = { shortcut ->
                    findNavController().navigate(
                        when (shortcut.destination) {
                            MENU -> HomeFragmentDirections.actionFragmentHomeToFragmentMenu()
                            MEMO -> HomeFragmentDirections.actionFragmentHomeToFragmentMemo()
                            REFRIGERATOR -> HomeFragmentDirections.actionFragmentHomeToFragmentRefrigerator()
                        }
                    )
                }
            )
        }
    }
}