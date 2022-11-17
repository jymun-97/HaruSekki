package com.jymun.harusekki.util.resources

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourcesProviderImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : ResourcesProvider {

    override fun getString(@StringRes resId: Int): String = context.getString(resId)

    override fun getStringArray(@ArrayRes resId: Int): Array<String> =
        context.resources.getStringArray(resId)

    override fun getColor(@ColorRes resId: Int): Int = ContextCompat.getColor(context, resId)

    override fun getDimension(@DimenRes resId: Int): Int =
        context.resources.getDimensionPixelOffset(resId)

    override fun getScreenWidth(): Int = context.resources.displayMetrics.widthPixels

    override fun getDrawable(@DrawableRes resId: Int): Drawable =
        ContextCompat.getDrawable(context, resId)!!

    override fun getDrawableIdArray(@ArrayRes resId: Int): TypedArray =
        context.resources.obtainTypedArray(resId)

}