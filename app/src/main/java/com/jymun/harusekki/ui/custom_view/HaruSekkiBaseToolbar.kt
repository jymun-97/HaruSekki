package com.jymun.harusekki.ui.custom_view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity.CENTER_VERTICAL
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import com.jymun.harusekki.R
import com.jymun.harusekki.databinding.HarusekkiBaseToolbarBinding

class HaruSekkiBaseToolbar(
    context: Context,
    attributeSet: AttributeSet
) : LinearLayout(context, attributeSet) {

    private val binding =
        HarusekkiBaseToolbarBinding.inflate(LayoutInflater.from(context), this)

    var toolbarText = ""
    var toolbarTextColor = 0
    var toolbarBackground: Drawable? = null
    var toolbarIcon: Drawable? = null
    var toolbarIconTint = 0

    init {
        initAttr(attributeSet)
        initView()
    }

    private fun initAttr(attr: AttributeSet) = context.theme.obtainStyledAttributes(
        attr, R.styleable.HaruSekkiBaseToolbar, 0, 0
    ).apply {
        try {
            toolbarText = getString(R.styleable.HaruSekkiBaseToolbar_toolbar_text)
                ?: context.getString(R.string.app_name)
            toolbarTextColor = getColor(
                R.styleable.HaruSekkiBaseToolbar_toolbar_text_color,
                context.getColor(R.color.white)
            )
            toolbarBackground = getDrawable(
                R.styleable.HaruSekkiBaseToolbar_toolbar_background
            ) ?: AppCompatResources.getDrawable(context, R.color.app_signature)
            toolbarIcon = getDrawable(R.styleable.HaruSekkiBaseToolbar_toolbar_icon)
                ?: AppCompatResources.getDrawable(context, R.drawable.ic_app_name)
            toolbarIconTint = getColor(
                R.styleable.HaruSekkiBaseToolbar_toolbar_icon_tint,
                context.getColor(R.color.white)
            )
        } finally {
            recycle()
        }
    }

    private fun initView() = binding.apply {
        this@HaruSekkiBaseToolbar.background = toolbarBackground
        this@HaruSekkiBaseToolbar.gravity = CENTER_VERTICAL
        this@HaruSekkiBaseToolbar.setPaddingRelative(10, 0, 10, 0)

        appNameTextView.text = toolbarText
        appNameTextView.setTextColor(toolbarTextColor)
        appNameImageView.setImageDrawable(toolbarIcon)
        appNameImageView.setColorFilter(toolbarIconTint)
    }
}