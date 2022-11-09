package com.jymun.harusekki.ui.base.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.jymun.harusekki.data.model.Model
import com.jymun.harusekki.data.model.ModelType
import com.jymun.harusekki.ui.base.adapter.viewholder.ModelViewHolder
import com.jymun.harusekki.ui.base.adapter.viewholder.ModelViewHolderMapper
import com.jymun.harusekki.util.resources.ResourcesProvider
import javax.inject.Inject

class ModelRecyclerAdapter<M : Model> @Inject constructor(
    private val resourcesProvider: ResourcesProvider
) : ListAdapter<Model, ModelViewHolder<M>>(Model.diffUtil) {

    private var adapterListener: AdapterListener? = null

    fun addAdapterListener(listener: AdapterListener) {
        adapterListener = listener
    }

    override fun getItemCount(): Int = currentList.size

    override fun getItemViewType(position: Int): Int = currentList[position].type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder<M> =
        ModelViewHolderMapper.map(parent, ModelType.values()[viewType], resourcesProvider)

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ModelViewHolder<M>, position: Int) =
        holder.bindData(currentList[position] as M, adapterListener)
}