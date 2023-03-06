package com.jymun.harusekki.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.jymun.harusekki.R
import com.jymun.harusekki.databinding.IngredientViewBinding
import com.jymun.harusekki.ui.extensions.BindingAdapters

class IngredientView(
    context: Context,
    attributeSet: AttributeSet
) : ConstraintLayout(context, attributeSet) {

    private var binding: IngredientViewBinding

    lateinit var imageView: ImageView
    var flag = false
    var selectedBorderColor: Int = 0
    var unselectedBorderColor: Int = 0
    var ingredientTitleText: String = ""
        set(value) {
            field = value
            binding.ingredientTextView.text = value
        }

    init {
        binding = IngredientViewBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
        initAttrs(attributeSet)
        initViews()
    }

    private fun initAttrs(attr: AttributeSet) = context.theme.obtainStyledAttributes(
        attr, R.styleable.IngredientView, 0, 0
    ).apply {
        try {
            selectedBorderColor = getColor(
                R.styleable.IngredientView_selected_border_color,
                context.getColor(R.color.app_signature)
            )
            unselectedBorderColor = getColor(
                R.styleable.IngredientView_unselected_border_color,
                context.getColor(R.color.gray)
            )
        } finally {
            recycle()
        }
    }

    private fun initViews() {
        this.imageView = binding.ingredientImageView

        binding.ingredientImageView.borderColor = unselectedBorderColor
        binding.checked.borderColor = selectedBorderColor
    }

    override fun isSelected(): Boolean {
        return flag
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

    override fun performClick(): Boolean {
        toggle()
        return super.performClick()
    }

    fun toggle() = with(binding.ingredientImageView) {
        flag = !flag
        binding.checked.visibility = if (flag) View.VISIBLE else View.GONE
    }

    companion object {

        @BindingAdapter("app:ingredient_image_url")
        @JvmStatic
        fun setImageSource(view: IngredientView, url: String?) {
            BindingAdapters.loadImage(view.imageView, url)
        }

        @BindingAdapter("app:ingredient_title")
        @JvmStatic
        fun setIngredientTitle(view: IngredientView, title: String) {
            view.ingredientTitleText = title
        }
    }
}