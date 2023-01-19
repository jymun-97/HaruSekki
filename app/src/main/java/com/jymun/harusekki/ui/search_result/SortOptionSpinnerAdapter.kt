package com.jymun.harusekki.ui.search_result

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import com.jymun.harusekki.R
import com.jymun.harusekki.databinding.ItemSortOptionBinding
import com.jymun.harusekki.ui.home.recipe.RecipeSortOption
import com.jymun.harusekki.util.resources.ResourcesProvider

class SortOptionSpinnerAdapter(
    context: Context,
    private val resourcesProvider: ResourcesProvider,
    @LayoutRes private val layoutResId: Int,
    private val values: Array<RecipeSortOption>,
    baseSortOption: RecipeSortOption
) : ArrayAdapter<RecipeSortOption>(context, layoutResId, values) {

    private var target = values.indexOf(baseSortOption)

    override fun getCount(): Int = values.size

    override fun getItem(position: Int): RecipeSortOption = values[position]

    fun updateTarget(position: Int) {
        target = position
        notifyDataSetChanged()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return ItemSortOptionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).apply {
            val option = values[position]

            optionImageView.visibility = View.GONE
            isSelectedImageView.visibility = View.GONE

            optionTextView.text = resourcesProvider.getString(option.textStrResId)
        }.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return ItemSortOptionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).apply {
            val option = values[position]

            optionImageView.setImageDrawable(resourcesProvider.getDrawable(option.imageResId))
            optionImageView.setColorFilter(
                if (position == target) resourcesProvider.getColor(R.color.black)
                else resourcesProvider.getColor(R.color.gray)
            )

            optionTextView.text = resourcesProvider.getString(option.textStrResId)
            optionTextView.setTextColor(
                if (position == target) resourcesProvider.getColor(R.color.black)
                else resourcesProvider.getColor(R.color.gray)
            )

            isSelectedImageView.visibility = if (position == target) View.VISIBLE else View.GONE
        }.root
    }
}