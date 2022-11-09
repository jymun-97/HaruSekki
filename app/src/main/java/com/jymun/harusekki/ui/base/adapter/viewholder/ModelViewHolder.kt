package com.jymun.harusekki.ui.base.adapter.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jymun.harusekki.data.model.Model
import com.jymun.harusekki.ui.base.adapter.AdapterListener
import com.jymun.harusekki.util.resources.ResourcesProvider
import javax.inject.Inject

abstract class ModelViewHolder<M : Model> @Inject constructor(
    binding: ViewDataBinding,
    protected val resourcesProvider: ResourcesProvider
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bindData(model: M, adapterListener: AdapterListener)
}