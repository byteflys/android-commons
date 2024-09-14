package io.github.hellogoogle2000.android.commons.ui

import android.graphics.Rect
import android.util.Size
import android.view.View
import io.github.hellogoogle2000.android.commons.context.ActivityEx.getFrontActivity
import io.github.hellogoogle2000.android.commons.context.ActivityEx.getTopContentView

object ViewLocator {

    fun getWindowSize(): Size {
        val activity = getFrontActivity()!!
        val metrics = activity.windowManager.maximumWindowMetrics
        metrics.windowInsets
        return Size(0, 0)
    }

    fun getApplicationSize(): Size {
        TODO()
    }

    fun getScreenSize(): Size {
        TODO()
    }

    fun View.getWindowOffset(): Location {
        val anotherView = context.getTopContentView()
        return getWindowOffset(anotherView)
    }

    fun View.getWindowOffset(anotherView: View): Location {
        val thisLocation = rootView.locationOnScreen()
        val anotherLocation = anotherView.locationOnScreen()
        val dx = thisLocation.x - anotherLocation.x
        val dy = thisLocation.y - anotherLocation.y
        return Location(dx = dx, dy = dy)
    }

    fun View.locationInParent(): Location {
        return Location(left, top)
    }

    fun View.locationInWindow(): Location {
        val out = IntArray(2)
        getLocationInWindow(out)
        return Location(out[0], out[1])
    }

    fun View.locationInApplication(): Location {
        TODO()
    }

    fun View.locationOnScreen(): Location {
        val out = IntArray(2)
        getLocationOnScreen(out)
        return Location(out[0], out[1])
    }

    fun View.visibleRectToSelf(): Rect {
        val rect = Rect()
        getLocalVisibleRect(rect)
        return rect
    }

    fun View.visibleRectInWindow(): Rect {
        val rect = Rect()
        getGlobalVisibleRect(rect)
        return rect
    }
}