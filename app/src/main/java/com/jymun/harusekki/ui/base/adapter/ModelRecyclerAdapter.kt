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
    private var modelList: List<Model>,
    private val resourcesProvider: ResourcesProvider,
    private val adapterListener: AdapterListener
) : ListAdapter<Model, ModelViewHolder<M>>(Model.diffUtil) {

    override fun getItemCount(): Int = modelList.size

    override fun getItemViewType(position: Int): Int = modelList[position].type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder<M> =
        ModelViewHolderMapper.map(parent, ModelType.values()[viewType], resourcesProvider)

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ModelViewHolder<M>, position: Int) =
        holder.bindData(modelList[position] as M, adapterListener)

    override fun submitList(list: MutableList<Model>?) {
        list?.let { modelList = it }
        super.submitList(list)
    }
}