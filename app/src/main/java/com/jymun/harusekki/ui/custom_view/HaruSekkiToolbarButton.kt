package com.jymun.harusekki.ui.custom_view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.jymun.harusekki.R
import com.jymun.harusekki.databinding.HarusekkiToolbarButtonBinding

class HaruSekkiToolbarButton(
    context: Context,
    attributeSet: AttributeSet
) : ConstraintLayout(context, attributeSet) {

    private var binding: HarusekkiToolbarButtonBinding

    var buttonTextEnabled: Boolean = false
    var buttonText: String = ""
    var buttonTextColor: Int = 0
    var buttonIcon: Drawable? = null
    var buttonIconTint: Int = 0

    init {
        binding = HarusekkiToolbarButtonBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
        initAttrs(attributeSet)
        initView()
    }

    private fun initAttrs(attr: AttributeSet) = context.theme.obtainStyledAttributes(
        attr, R.styleable.HaruSekkiToolbarButton, 0, 0
    ).apply {
        try {
            buttonTextEnabled =
                getBoolean(R.styleable.HaruSekkiToolbarButton_button_text_enabled, false)
            buttonText = getString(R.styleable.HaruSekkiToolbarButton_button_text) ?: ""
            buttonTextColor = getColor(
                R.styleable.HaruSekkiToolbarButton_button_text_color,
                context.getColor(R.color.white)
            )
            buttonIcon = getDrawable(R.styleable.HaruSekkiToolbarButton_button_icon)
            buttonIconTint = getColor(
                R.styleable.HaruSekkiToolbarButton_button_icon_tint,
                context.getColor(R.color.white)
            )
        } finally {
            recycle()
        }
    }

    private fun initView() = binding.apply {
        buttonTextView.text = buttonText
        buttonTextView.setTextColor(buttonTextColor)
        buttonTextView.visibility = if (buttonTextEnabled) View.VISIBLE else View.GONE
        buttonImageView.setImageDrawable(buttonIcon)
        buttonImageView.setColorFilter(buttonIconTint)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        binding.root.setOnClickListener(l)
    }
}