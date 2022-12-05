package com.jymun.harusekki.ui.refrigerator

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.jymun.harusekki.databinding.FragmentRefrigeratorBinding
import com.jymun.harusekki.ui.base.BaseFragment

class RefrigeratorFragment : BaseFragment<RefrigeratorViewModel, FragmentRefrigeratorBinding>() {

    override val viewModel: RefrigeratorViewModel by viewModels()

    override fun getViewDataBinding() = FragmentRefrigeratorBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tempTextView.text = "나의 냉장고"
    }

    override fun setUpBinding() = binding.apply {
        // TODO("Not yet implemented")
    }

    override fun observeState() {
        // TODO("Not yet implemented")
    }
}