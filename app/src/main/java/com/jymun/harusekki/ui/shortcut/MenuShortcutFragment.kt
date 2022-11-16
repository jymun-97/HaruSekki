package com.jymun.harusekki.ui.shortcut

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.jymun.harusekki.databinding.ShortcutMenuBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.menu.MenuViewModel

class MenuShortcutFragment : BaseFragment<MenuViewModel, ShortcutMenuBinding>() {

    override val viewModel: MenuViewModel by activityViewModels()

    override fun getViewDataBinding() = ShortcutMenuBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}