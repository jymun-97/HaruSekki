package com.jymun.harusekki.util.resources

import androidx.annotation.ColorRes
import androidx.annotation.StringRes

interface ResourcesProvider {

    fun getString(@StringRes resId: Int): String

    fun getColor(@ColorRes resId: Int): Int
}