package com.jymun.harusekki.ui.custom_view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import com.jymun.harusekki.R
import com.jymun.harusekki.databinding.HarusekkiBaseToolbarBinding

class HaruSekkiBaseToolbar(
    context: Context,
    attributeSet: AttributeSet
) : Toolbar(context, attributeSet) {

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

        appNameTextView.text = toolbarText
        appNameTextView.setTextColor(toolbarTextColor)
        appNameImageView.setImageDrawable(toolbarIcon)
        appNameImageView.setColorFilter(toolbarIconTint)
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (child is HaruSekkiToolbarButton) {
            binding.toolbarButtonContainer.addView(child, index, params)
        } else {
            super.addView(child, index, params)
        }
    }
}