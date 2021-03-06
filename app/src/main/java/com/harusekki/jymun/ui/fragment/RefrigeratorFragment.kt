package com.harusekki.jymun.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.harusekki.jymun.databinding.FragmentRefrigeratorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RefrigeratorFragment : Fragment() {

    private lateinit var binding: FragmentRefrigeratorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRefrigeratorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}