package com.jymun.harusekki.ui.extensions

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

class SnapToStartHelper : PagerSnapHelper() {
    private var horizontalHelper: OrientationHelper? = null

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager?): View? {
        return getStartView(
            layoutManager as LinearLayoutManager,
            getHorizontalHelper(layoutManager)
        )
    }

    private fun getStartView(layoutManager: LinearLayoutManager, helper: OrientationHelper): View? {
        val firstVisibleChildPosition = layoutManager.findFirstVisibleItemPosition()
        val lastCompletelyVisibleChildPosition =
            layoutManager.findLastCompletelyVisibleItemPosition()
        val lastChildPosition = layoutManager.itemCount - 1

        if (firstVisibleChildPosition != RecyclerView.NO_POSITION) {
            var childView = layoutManager.findViewByPosition(firstVisibleChildPosition)
            if (helper.getDecoratedEnd(childView) < helper.getDecoratedMeasurement(childView) / 2) {
                childView = layoutManager.findViewByPosition(firstVisibleChildPosition + 1)
            } else if (lastCompletelyVisibleChildPosition == lastChildPosition) {
                childView = layoutManager.findViewByPosition(lastChildPosition)
            }
            return childView
        }
        return null
    }

    override fun calculateDistanceToFinalSnap(
        layoutManager: RecyclerView.LayoutManager,
        targetView: View
    ): IntArray =
        intArrayOf(distanceToStart(targetView, getHorizontalHelper(layoutManager)), 0)

    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager,
        velocityX: Int,
        velocityY: Int
    ): Int {
        val currentView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        val currentPosition = layoutManager.getPosition(currentView)

        return if (velocityX < 0) {
            (currentPosition - 1).coerceAtLeast(0)
        } else {
            (currentPosition + 1).coerceAtMost(layoutManager.itemCount - 1)
        }
    }

    private fun distanceToStart(targetView: View, helper: OrientationHelper): Int =
        helper.getDecoratedStart(targetView) - helper.startAfterPadding

    private fun getHorizontalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        if (horizontalHelper == null) {
            horizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager)
        }
        return horizontalHelper!!
    }
}