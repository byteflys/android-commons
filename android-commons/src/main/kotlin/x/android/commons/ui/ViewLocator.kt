package x.android.commons.ui

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.Size
import android.view.View
import android.view.WindowManager
import x.android.commons.context.ActivityX.getActivityRootView

object ViewLocator {

    private val defaultSize = Size(0, 0)
    private val defaultRect = Rect()

    fun View.getWindowSize(): Size {
        return Size(rootView.measuredWidth, rootView.measuredHeight)
    }

    fun Context.getActivitySize(): Size {
        val activity = this as? Activity
        activity ?: throw RuntimeException("ui context required")
        val activityRootView = getActivityRootView()
        return Size(activityRootView.measuredWidth, activityRootView.measuredHeight)
    }

    fun Context.getApplicationSize(): Size {
        val windowManager = getSystemService(WindowManager::class.java)
        val bounds = windowManager.currentWindowMetrics.bounds
        return Size(bounds.width(), bounds.height())
    }

    fun Context.getScreenSize(): Size {
        val windowManager = getSystemService(WindowManager::class.java)
        val bounds = windowManager.maximumWindowMetrics.bounds
        return Size(bounds.width(), bounds.height())
    }

    // offset to parent window
    fun View.getWindowOffset(): Location {
        val activity = context as? Activity
        activity ?: throw RuntimeException("ui context required")
        val activityRootView = getRootView()
        return getWindowOffset(activityRootView)
    }

    // must in same activity
    fun View.getWindowOffset(anotherView: View): Location {
        val thisLocation = rootView.locationInActivity()
        val anotherLocation = anotherView.locationInActivity()
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

    fun View.locationInActivity(): Location {
        val locationInWindow = locationInWindow()
        val windowOffset = getWindowOffset()
        return Location(locationInWindow.x + windowOffset.dx, locationInWindow.y + windowOffset.dy)
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

    fun View.rectInParent(): Rect {
        TODO()
    }

    fun View.rectInWindow(): Rect {
        val rect = Rect()
        getGlobalVisibleRect(rect)
        return rect
    }

    fun View.rectInActivity(): Rect {
        TODO()
    }

    fun View.rectOnScreen(): Rect {
        TODO()
    }
}