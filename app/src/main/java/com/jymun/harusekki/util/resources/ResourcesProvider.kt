package com.jymun.harusekki.util.resources

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import androidx.annotation.*

interface ResourcesProvider {

    fun getString(@StringRes resId: Int): String

    fun getStringArray(@ArrayRes resId: Int): Array<String>

    fun getColor(@ColorRes resId: Int): Int

    fun getDimension(@DimenRes resId: Int): Int

    fun getScreenWidth(): Int

    fun getDrawable(@DrawableRes resId: Int): Drawable

    fun getDrawableIdArray(@ArrayRes resId: Int): TypedArray
}