package com.jymun.harusekki.ui.memo

import com.jymun.harusekki.ui.base.adapter.AdapterListener
import com.jymun.harusekki.ui.custom_view.OnMemoChangedListener

interface MemoAdapterListener : AdapterListener {

    val onMemoChangedListener: OnMemoChangedListener
}