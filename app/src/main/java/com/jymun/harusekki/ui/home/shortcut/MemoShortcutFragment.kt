package com.jymun.harusekki.ui.home.shortcut

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.jymun.harusekki.databinding.ShortcutMemoBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.home.HomeFragmentDirections
import com.jymun.harusekki.ui.memo.MemoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemoShortcutFragment : BaseFragment<MemoViewModel, ShortcutMemoBinding>() {

    override val viewModel: MemoViewModel by viewModels()

    override fun getViewDataBinding() = ShortcutMemoBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMemoSeekbar()
        initMoveToMemoFragmentButton()

        viewModel.loadAllMemo()
    }

    override fun setUpBinding() = binding.apply {
        viewModel = this@MemoShortcutFragment.viewModel
        lifecycleOwner = viewLifecycleOwner
    }

    override fun observeState() {}

    private fun initMemoSeekbar() = binding.memoSeekbar.apply {
        isEnabled = false
    }

    private fun initMoveToMemoFragmentButton() = binding.moveToMenoFragmentButton.apply {
        setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionFragmentHomeToFragmentMemo()
            )
        }
    }
}