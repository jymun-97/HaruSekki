package com.jymun.harusekki.ui.home.shortcut

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.jymun.harusekki.databinding.ShortcutRefrigeratorBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.menu.MenuViewModel

class RefrigeratorShortcutFragment : BaseFragment<MenuViewModel, ShortcutRefrigeratorBinding>() {

    override val viewModel: MenuViewModel by activityViewModels()

    override fun getViewDataBinding() = ShortcutRefrigeratorBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}