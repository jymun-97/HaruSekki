package com.jymun.harusekki.ui.memo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.jymun.harusekki.databinding.FragmentMemoBinding
import com.jymun.harusekki.ui.base.BaseFragment

class MemoFragment : BaseFragment<MemoViewModel, FragmentMemoBinding>() {

    override val viewModel: MemoViewModel by viewModels()

    override fun getViewDataBinding() = FragmentMemoBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tempTextView.text = "장보기 메모"
    }

    override fun setUpBinding() = binding.apply {
        // TODO("Not yet implemented")
    }

    override fun observeState() {
        // TODO("Not yet implemented")
    }
}