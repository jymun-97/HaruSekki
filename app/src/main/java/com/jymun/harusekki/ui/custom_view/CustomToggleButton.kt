package com.jymun.harusekki.ui.custom_view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView
import com.jymun.harusekki.R

class CustomToggleButton(
    context: Context,
    attributeSet: AttributeSet
) : AppCompatTextView(context, attributeSet) {

    private lateinit var toggleBackground: Drawable
    private lateinit var selectedBackground: Drawable
    private var flag = false

    init {
        initAttrs(attributeSet)
        initView()
    }

    private fun initAttrs(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomToggleButton,
            0, 0
        ).apply {
            try {
                toggleBackground = getDrawable(R.styleable.CustomToggleButton_toggle_background)!!
                selectedBackground =
                    getDrawable(R.styleable.CustomToggleButton_selected_background)!!
            } finally {
                recycle()
            }
        }
    }

    private fun initView() {
        background = toggleBackground
    }

    override fun performClick(): Boolean {
        (parent as CustomToggleGroup).updateSelectedButton(if (flag) null else this)
        return super.performClick()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> true

            MotionEvent.ACTION_UP -> {
                performClick()
                true
            }

            else -> false
        }
    }

    fun toggle() {
        flag = !flag
        background = if (flag) selectedBackground else toggleBackground
    }
}