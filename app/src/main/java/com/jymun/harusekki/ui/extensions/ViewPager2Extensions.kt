package com.jymun.harusekki.ui.extensions

import androidx.annotation.DimenRes
import androidx.viewpager2.widget.ViewPager2

private fun ViewPager2.enableShowingOtherPages(numberOfPages: Int) {
    clipToPadding = false
    clipChildren = false
    offscreenPageLimit = numberOfPages
}

fun ViewPager2.showOtherPages(
    numberOfPages: Int,
    @DimenRes pageMargin: Int,
    @DimenRes pageWidth: Int,
    screenWidth: Int
) {
    enableShowingOtherPages(numberOfPages)

    val nextPageVisible = screenWidth - pageWidth - pageMargin * 2
    setPadding(pageMargin, 0, nextPageVisible, 0)
    setPageTransformer { page, position ->
        page.translationX = pageMargin * position
        when (currentItem) {
            0 -> setPadding(pageMargin, 0, nextPageVisible, 0)

            adapter!!.itemCount - 1 -> setPadding(nextPageVisible, 0, pageMargin, 0)

            else -> setPadding(pageMargin * 2, 0, nextPageVisible - pageMargin, 0)
        }
    }
}