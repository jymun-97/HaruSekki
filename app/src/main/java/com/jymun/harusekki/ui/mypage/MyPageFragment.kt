package com.jymun.harusekki.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.jymun.harusekki.databinding.FragmentMyPageBinding
import com.jymun.harusekki.ui.base.BaseFragment

class MyPageFragment : BaseFragment<MyPageViewModel, FragmentMyPageBinding>() {

    override val viewModel: MyPageViewModel by viewModels()

    override fun getViewDataBinding() = FragmentMyPageBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tempTextView.text = "마이페이지"
    }

    override fun setUpBinding() = binding.apply {
        // TODO("Not yet implemented")
    }

    override fun observeState() {
        // TODO("Not yet implemented")
    }
}