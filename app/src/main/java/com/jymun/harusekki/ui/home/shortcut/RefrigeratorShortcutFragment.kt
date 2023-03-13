package com.jymun.harusekki.ui.home.shortcut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jymun.harusekki.databinding.ShortcutRefrigeratorBinding
import com.jymun.harusekki.ui.home.HomeFragmentDirections

class RefrigeratorShortcutFragment : Fragment() {

    private var _binding: ShortcutRefrigeratorBinding? = null
    private val binding: ShortcutRefrigeratorBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ShortcutRefrigeratorBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAddIngredientButton()
        initShowDetailButton()
    }

    private fun initAddIngredientButton() = binding.addIngredientButton.apply {
        setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionFragmentHomeToFragmentIngredient()
            )
        }
    }

    private fun initShowDetailButton() = binding.showDetailButton.setOnClickListener {
        findNavController().navigate(
            HomeFragmentDirections.actionFragmentHomeToFragmentRefrigerator()
        )
    }
}