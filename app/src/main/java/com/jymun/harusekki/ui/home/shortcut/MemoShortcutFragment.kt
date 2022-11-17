package com.jymun.harusekki.ui.home.shortcut

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.jymun.harusekki.databinding.ShortcutMemoBinding
import com.jymun.harusekki.ui.base.BaseFragment
import com.jymun.harusekki.ui.menu.MenuViewModel

class MemoShortcutFragment : BaseFragment<MenuViewModel, ShortcutMemoBinding>() {

    override val viewModel: MenuViewModel by activityViewModels()

    override fun getViewDataBinding() = ShortcutMemoBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}