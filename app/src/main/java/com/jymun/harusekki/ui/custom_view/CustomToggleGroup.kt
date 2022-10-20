package com.jymun.harusekki.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

class CustomToggleGroup(
    context: Context,
    attributeSet: AttributeSet? = null
) : LinearLayout(context, attributeSet) {

    private var selectedButton: CustomToggleButton? = null

    init {
        initView()
    }

    private fun initView() {
        orientation = HORIZONTAL
        isClickable = false
    }

    override fun addView(child: View?) {
        if (child !is CustomToggleButton) return
        super.addView(child)
    }

    fun updateSelectedButton(target: CustomToggleButton?) {
        selectedButton?.toggle()
        selectedButton = target.also { it?.toggle() }
    }
}