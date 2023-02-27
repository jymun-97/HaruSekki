package com.jymun.harusekki.ui.custom_view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import com.jymun.harusekki.R
import com.jymun.harusekki.databinding.CustomButtonWithEdittextBinding

class CustomButtonWithEditText(
    context: Context, attributeSet: AttributeSet
) : LinearLayout(context, attributeSet) {

    private val binding = CustomButtonWithEdittextBinding.inflate(
        LayoutInflater.from(context), this
    )

    var buttonIcon: Drawable? = null
    var buttonIconTint: Int = 0
    var editTextColor: Int = 0

    init {
        initAttrs(attributeSet)
        initViews()
    }

    private fun initAttrs(attr: AttributeSet) = context.theme.obtainStyledAttributes(
        attr, R.styleable.CustomButtonWithEditText, 0, 0
    ).apply {
        try {
            buttonIcon = getDrawable(R.styleable.CustomButtonWithEditText_button_icon)
            buttonIconTint = getColor(
                R.styleable.CustomButtonWithEditText_button_icon_tint,
                context.getColor(R.color.app_signature)
            )
            editTextColor = getColor(
                R.styleable.CustomButtonWithEditText_edit_text_color,
                context.getColor(R.color.app_signature)
            )
        } finally {
            recycle()
        }
    }

    private fun initViews() {
        this.orientation = VERTICAL
        this.gravity = Gravity.CENTER
        initEditText()
        initButton()
    }

    private fun initEditText() = binding.editText.apply {
        setTextColor(editTextColor)
        visibility = View.GONE
        text = null

        setOnFocusChangeListener { _, isFocused ->
            if (isFocused) {
                visibility = View.VISIBLE
                binding.button.visibility = View.GONE
                showKeyboard()
            } else {
                visibility = View.GONE
                binding.button.visibility = View.VISIBLE
                hideKeyboard()
            }
        }
    }

    private fun initButton() = binding.button.apply {
        background = buttonIcon?.apply { setTint(buttonIconTint) }
        visibility = View.VISIBLE

        setOnClickListener {
            binding.editText.visibility = View.VISIBLE
            binding.editText.requestFocus()
        }
    }

    private fun showKeyboard() {
        val imm: InputMethodManager = context.getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        imm.showSoftInput(binding.editText, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.editText.windowToken, 0)
    }

    fun addOnEnterClicked(
        onEmptyText: () -> Unit,
        onEnterClicked: (String) -> Unit,
    ) = binding.editText.apply {
        setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE && !text.isNullOrEmpty()) {
                onEnterClicked(text.toString())
                clearFocus()
            } else {
                onEmptyText()
            }
            true
        }
    }
}