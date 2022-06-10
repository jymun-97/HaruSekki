package com.harusekki.jymun.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harusekki.jymun.R
import com.harusekki.jymun.data.model.Category
import com.harusekki.jymun.databinding.ItemCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val categoryList: ArrayList<Category> by lazy {
        arrayListOf(
            Category(
                title = "밥",
                image = R.drawable.img_category_rice
            ),
            Category(
                title = "반찬",
                image = R.drawable.img_category_side
            ),
            Category(
                title = "국/찌개",
                image = R.drawable.img_category_soup
            ),
            Category(
                title = "볶음",
                image = R.drawable.img_category_stir
            ),
            Category(
                title = "면",
                image = R.drawable.img_category_noodle
            ),
            Category(
                title = "기타",
                image = R.drawable.img_category_others
            )
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        return ViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class ViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.category = category
        }
    }
}