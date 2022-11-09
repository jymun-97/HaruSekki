package com.jymun.harusekki.ui.base.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jymun.harusekki.data.model.Model
import com.jymun.harusekki.data.model.ModelType
import com.jymun.harusekki.util.resources.ResourcesProvider

object ModelViewHolderMapper {

    fun <M : Model> map(
        parent: ViewGroup,
        type: ModelType,
        resourcesProvider: ResourcesProvider
    ): ModelViewHolder<M> {
        val inflater = LayoutInflater.from(parent.context)
        return when (type) {
            else -> {}
        } as ModelViewHolder<M>
    }
}