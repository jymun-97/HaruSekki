package com.harusekki.jymun.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.harusekki.jymun.databinding.FragmentMemoBinding
import com.harusekki.jymun.ui.viewmodel.MemoViewModel

class MemoFragment : Fragment() {

    private lateinit var binding: FragmentMemoBinding
    private lateinit var memoViewModel: MemoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        memoViewModel = ViewModelProvider(requireActivity())[MemoViewModel::class.java]

        binding.apply {
            memoSeekBar.isEnabled = false

            memoViewModel = this@MemoFragment.memoViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }
}