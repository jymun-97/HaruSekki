package com.jymun.harusekki.ui.menu

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.time.LocalDate

class MenuPageAdapter(
    fragmentActivity: FragmentActivity,
    private val onMenuAdded: () -> Unit
) : FragmentStateAdapter(fragmentActivity) {

    private var date = LocalDate.now()
    val defaultPosition = Int.MAX_VALUE / 2

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun createFragment(position: Int) = MenuPageFragment(
        date = date.plusDays(position - defaultPosition.toLong()),
        onMenuAdded = onMenuAdded
    )
}