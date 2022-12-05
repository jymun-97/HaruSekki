package com.jymun.harusekki.ui.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.jymun.harusekki.databinding.FragmentMenuBinding
import com.jymun.harusekki.ui.base.BaseFragment

class MenuFragment : BaseFragment<MenuViewModel, FragmentMenuBinding>() {

    override val viewModel: MenuViewModel by viewModels()

    override fun getViewDataBinding() = FragmentMenuBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tempTextView.text = "식단 관리"
    }

    override fun setUpBinding() = binding.apply {
        // TODO("Not yet implemented")
    }

    override fun observeState() {
        // TODO("Not yet implemented")
    }
}