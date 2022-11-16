package com.jymun.harusekki.util.resources

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
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

    override fun getColor(@ColorRes resId: Int): Int = ContextCompat.getColor(context, resId)

    override fun getDimension(@DimenRes resId: Int): Int =
        context.resources.getDimensionPixelOffset(resId)

    override fun getScreenWidth(): Int = context.resources.displayMetrics.widthPixels

}