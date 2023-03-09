package com.jymun.harusekki.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import com.jymun.harusekki.R
import com.jymun.harusekki.data.model.memo.Memo
import com.jymun.harusekki.databinding.MemoViewBinding

class MemoView(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    private var memo = Memo.getEmptyMemo()
        set(value) {
            field = value
            binding.apply {
                memoCheckBox.isChecked = field.isChecked
                checkedLine.visibility = if (field.isChecked) View.VISIBLE else View.GONE
                memoEditText.setText(field.text)

                if (memo.isEmptyMemo()) onEditMode()
                else offEditMode()
            }
        }

    private var binding: MemoViewBinding
    private var onMemoChangedListener: OnMemoChangedListener? = null

    init {
        binding = MemoViewBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
        initViews()
    }

    private fun initViews() = binding.apply {
        initMemoCheckBox()
        initMemoEditText()
        initOpenOptionButton()
    }

    private fun initMemoCheckBox() = binding.apply {
        memoCheckBox.setOnCheckedChangeListener { _, isChecked ->
            memo = memo.copy(isChecked = isChecked)
            onMemoChangedListener?.onMemoCheckedChanged(memo)
        }
    }

    private fun initMemoEditText() = binding.memoEditText.apply {
        setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                setSelection(text.length)
                onEditMode()
                showKeyboard()
            } else {
                offEditMode()
                hideKeyboard()
            }
        }
        setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE && !text.isNullOrEmpty()) {
                finishEditMemo()
                clearFocus()
            } else {
            }
            true
        }
        doOnTextChanged { text, _, _, _ ->
            onMemoChangedListener?.onMemoTextChanged(text?.toString() ?: "")
        }
    }

    private fun initOpenOptionButton() = binding.openOptionButton.setOnClickListener {
        PopupMenu(context, binding.openOptionButton).apply {
            menuInflater.inflate(R.menu.edit_menu_options, menu)
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.edit_memo -> binding.memoEditText.requestFocus()

                    R.id.delete_memo -> onMemoChangedListener?.onMemoDeleted(memo)
                }
                true
            }
        }.show()
    }

    private fun finishEditMemo() = binding.apply {
        memo = memo.copy(text = memoEditText.text.toString())
        onMemoChangedListener?.onMemoUpdated(memo)
    }

    private fun onEditMode() = binding.apply {
        memoCheckBox.isEnabled = false
    }

    private fun offEditMode() = binding.apply {
        memoEditText.setText(memo.text)
        memoCheckBox.isEnabled = true
    }

    private fun showKeyboard() {
        val imm: InputMethodManager = context.getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        imm.showSoftInput(binding.memoEditText, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.memoEditText.windowToken, 0)
    }

    fun registerMemoChangedListener(listener: OnMemoChangedListener) {
        onMemoChangedListener = listener
        initViews()
    }

    fun submitList(dataSet: List<String>) = binding.memoEditText.setAdapter(
        ArrayAdapter(
            context,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            dataSet
        )
    )

    companion object {
        @BindingAdapter("app:memo_model")
        @JvmStatic
        fun setMemo(view: MemoView, memo: Memo) {
            view.memo = memo
        }
    }
}