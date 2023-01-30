package com.jymun.harusekki.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

class SearchModeToggleGroup(
    context: Context,
    attributeSet: AttributeSet? = null
) : LinearLayout(context, attributeSet) {

    private var selectedButton: SearchModeToggleButton? = null
    private var onSearchModeChangedListener: OnSearchModeChangedListener? = null

    init {
        initView()
    }

    private fun initView() {
        orientation = HORIZONTAL
        isClickable = false
    }

    override fun addView(child: View?) {
        if (child !is SearchModeToggleButton) return
        super.addView(child)
    }

    fun updateSelectedButton(target: SearchModeToggleButton?) {
        selectedButton?.toggle()
        selectedButton = target.also { it?.toggle() }

        onSearchModeChangedListener?.onSearchModeChanged(selectedButton?.id)
    }

    fun setOnSearchModeChangedListener(listener: OnSearchModeChangedListener) {
        onSearchModeChangedListener = listener
    }
}