package com.jymun.harusekki.util.resources

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ResourcesProvider {

    fun getString(@StringRes resId: Int): String

    fun getColor(@ColorRes resId: Int): Int

    fun getDimension(@DimenRes resId: Int): Int

    fun getScreenWidth(): Int

    fun getDrawable(@DrawableRes resId: Int): Drawable
}