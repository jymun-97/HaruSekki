package com.jymun.harusekki.ui.detail

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.jymun.harusekki.data.model.menu.MenuCategory
import com.jymun.harusekki.databinding.DialogAddMenuBinding
import com.jymun.harusekki.ui.extensions.toCalendarDay
import com.jymun.harusekki.ui.extensions.toLocalDate
import java.time.LocalDate

class AddMenuDialog(
    context: Context,
    private val onAddButtonClicked: (year: Int, month: Int, dayOfMonth: Int, menuCategory: MenuCategory) -> Unit
) : Dialog(context) {

    private lateinit var binding: DialogAddMenuBinding
    private var selectedDate = LocalDate.now()
    private var selectedMenuCategory = MenuCategory.BREAKFAST

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogAddMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDatePicker()
        initMenuCategoryButtons()
        initAddButton()
        initCancelButton()
    }

    private fun initDatePicker() = binding.datePicker.apply {
        selectedDate = this@AddMenuDialog.selectedDate.toCalendarDay()
        setOnDateChangedListener { _, date, _ ->
            this@AddMenuDialog.selectedDate = date.toLocalDate()
        }
    }

    private fun initMenuCategoryButtons() = binding.menuCategoryToggleGroup.apply {
        addOnButtonCheckedListener { _, checkedId, _ ->
            selectedMenuCategory = when (checkedId) {
                binding.breakfastButton.id -> MenuCategory.BREAKFAST

                binding.lunchButton.id -> MenuCategory.LUNCH

                else -> MenuCategory.DINNER
            }
        }
    }

    private fun initAddButton() = binding.addButton.setOnClickListener {
        onAddButtonClicked(
            selectedDate.year,
            selectedDate.monthValue,
            selectedDate.dayOfMonth,
            selectedMenuCategory
        )
        dismiss()
    }

    private fun initCancelButton() = binding.cancelButton.setOnClickListener {
        dismiss()
    }
}