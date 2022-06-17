package com.harusekki.jymun.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.harusekki.jymun.data.model.TestModel
import com.harusekki.jymun.databinding.ItemTestModelBinding

class MemoAdapter : ListAdapter<TestModel, MemoAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTestModelBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(
        private val binding: ItemTestModelBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: TestModel) {
            binding.model = model
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<TestModel>() {
            override fun areItemsTheSame(oldItem: TestModel, newItem: TestModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TestModel, newItem: TestModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}