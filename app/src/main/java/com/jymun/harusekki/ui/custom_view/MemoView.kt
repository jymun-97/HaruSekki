package com.jymun.harusekki.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import com.jymun.harusekki.R
import com.jymun.harusekki.data.model.ingredient.Ingredient
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

                if (memo.isEmptyMemo()) memoEditText.requestFocus()
            }
        }

    private var binding: MemoViewBinding
    private var onMemoChangedListener: OnMemoChangedListener? = null

    private val adapter = Adapter(emptyList())
    private var ingredientList = emptyList<Ingredient>()

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
            if (memo.isChecked == isChecked) return@setOnCheckedChangeListener

            memo = memo.copy(isChecked = isChecked)
            onMemoChangedListener?.onMemoCheckedChanged(memo)
        }
    }

    private fun initMemoEditText() = binding.memoEditText.apply {
        dropDownVerticalOffset = 20
        dropDownHorizontalOffset = -20
        threshold = 1

        setAdapter(this@MemoView.adapter)
        setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.root.background =
                    ContextCompat.getDrawable(context, R.drawable.shape_signature_round_rectangle)

                setSelection(text.length)
                showKeyboard()
            } else {
                binding.root.background =
                    ContextCompat.getDrawable(context, R.drawable.shape_gray_round_rectangle)

                finishEditMemo()
                if (memo.isEmptyMemo()) {
                    onMemoChangedListener?.onMemoDeleted(memo)
                    return@setOnFocusChangeListener
                }
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
            onMemoChangedListener?.onMemoTextChanged(text?.toString() ?: "") { dataSet ->
                this@MemoView.adapter.setData(dataSet.map { it.title })
                ingredientList = dataSet
            }
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
        val text = memoEditText.text.toString()
        if (text == memo.text || text.isEmpty()) return@apply

        memo = if (ingredientList.isNotEmpty() && text == ingredientList.first().title) {
            memo.copy(
                text = text,
                ingredient = ingredientList.first()
            )
        } else {
            memo.copy(
                text = text,
                ingredient = null
            )
        }
        onMemoChangedListener?.onMemoUpdated(memo)
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

    private inner class Adapter(
        private var data: List<String>,
    ) : ArrayAdapter<String>(
        context,
        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
        data
    ) {
        override fun getItem(position: Int): String = data[position]

        override fun getCount(): Int = data.size

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            return TextView(context).apply {
                setPadding(20, 20, 20, 20)
                text = data[position]
                setTextColor(context.getColor(R.color.gray))
            }
        }

        fun setData(newData: List<String>) {
            data = newData
            notifyDataSetChanged()
        }
    }

    companion object {
        @BindingAdapter("app:memo_model")
        @JvmStatic
        fun setMemo(view: MemoView, memo: Memo) {
            view.memo = memo
        }
    }
}