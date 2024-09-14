package io.github.hellogoogle2000.android.commons.ui

import android.graphics.Rect
import android.view.View

object ViewLocator {

    fun View.locationInParent(): Location {
        return Location(left, top)
    }

    fun View.locationInWindow(): Location {
        val out = IntArray(2)
        getLocationInWindow(out)
        return Location(out[0], out[1])
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