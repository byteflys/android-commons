package x.android.commons.ui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Rect
import android.util.Size
import android.view.View
import android.view.WindowManager
import androidx.annotation.UiContext

// android sdk only provides these info
// location to parent, location in window, location on screen
// view bounds, app bounds, screen bounds
// other info can only be inferred from these
object ViewLocator {

    @UiContext
    fun Context.asActivity(): Activity {
        var context = this
        if (context !is Activity && context is ContextWrapper) {
            context = context.baseContext
        }
        val activity = context as? Activity
        return activity ?: throw RuntimeException("ui context required")
    }

    @UiContext
    fun Context.getActivityDecorView() = asActivity().window.decorView

    @UiContext
    fun Context.getActivityContentView(): View {
        return asActivity().findViewById(android.R.id.content)
    }

    @UiContext
    fun View.getActivity() = context.asActivity()

    fun View.getWindowDecorView() = rootView

    @UiContext
    fun View.getActivityDecorView() = context.getActivityDecorView()

    @UiContext
    fun View.getActivityContentView() = context.getActivityContentView()

    fun View.getWindowSize(): Size {
        val decorView = getWindowDecorView()
        return Size(decorView.measuredWidth, decorView.measuredHeight)
    }

    @UiContext
    fun Context.getActivityContentSize(): Size {
        val activityContentView = getActivityContentView()
        return Size(activityContentView.measuredWidth, activityContentView.measuredHeight)
    }

    @UiContext
    fun Context.getActivityWindowSize(): Size {
        val activityDecorView = getActivityDecorView()
        return Size(activityDecorView.measuredWidth, activityDecorView.measuredHeight)
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

    // offset from current window to parent window
    @UiContext
    fun View.getWindowOffset(): WindowLocation {
        val activityDecorView = getActivityDecorView()
        return getWindowOffset(activityDecorView)
    }

    // must in same activity
    @UiContext
    fun View.getWindowOffset(anotherView: View): WindowLocation {
        val thisLocation = getWindowDecorView().locationOnScreen()
        val anotherLocation = anotherView.getWindowDecorView().locationOnScreen()
        val dx = thisLocation.x - anotherLocation.x
        val dy = thisLocation.y - anotherLocation.y
        return WindowLocation(dx = dx, dy = dy)
    }

    // offset from activity content to activity window
    @UiContext
    fun Context.getContentOffset(): WindowLocation {
        val activityContentView = getActivityContentView()
        return activityContentView.locationInWindow()
    }

    // offset from current activity to application
    @UiContext
    fun Context.getActivityOffset(): WindowLocation {
        val windowManager = getSystemService(WindowManager::class.java)
        val applicationBound = windowManager.currentWindowMetrics.bounds
        val activityLocation = getActivityDecorView().locationOnScreen()
        val dx = activityLocation.x - applicationBound.left
        val dy = activityLocation.y - applicationBound.top
        return WindowLocation(dx = dx, dy = dy)
    }

    fun View.locationInParent(): WindowLocation {
        return WindowLocation(left, top)
    }

    fun View.locationInWindow(): WindowLocation {
        val out = IntArray(2)
        getLocationInWindow(out)
        return WindowLocation(out[0], out[1])
    }

    @UiContext
    fun View.locationInActivity(): WindowLocation {
        val locationInWindow = locationInWindow()
        val windowOffset = getWindowOffset()
        return WindowLocation(locationInWindow.x + windowOffset.dx, locationInWindow.y + windowOffset.dy)
    }

    @UiContext
    fun View.locationInApplication(): WindowLocation {
        val locationInActivity = locationInActivity()
        val activityOffset = context.getActivityOffset()
        return WindowLocation(locationInActivity.x + activityOffset.dx, locationInActivity.y + activityOffset.dy)
    }

    fun View.locationOnScreen(): WindowLocation {
        val out = IntArray(2)
        getLocationOnScreen(out)
        return WindowLocation(out[0], out[1])
    }

    fun View.visibleRectToSelf(): Rect {
        val rect = Rect()
        getLocalVisibleRect(rect)
        return rect
    }

    fun View.rectInParent(): Rect {
        val location = locationInParent()
        return Rect(location.x, location.y, location.x + measuredWidth, location.y + measuredHeight)
    }

    fun View.rectInWindow(): Rect {
        val rect = Rect()
        getGlobalVisibleRect(rect)
        return rect
    }

    @UiContext
    fun View.rectInActivity(): Rect {
        val location = locationInActivity()
        return Rect(location.x, location.y, location.x + measuredWidth, location.y + measuredHeight)
    }

    // pixel size may be scaled in multi-window mode
    fun View.rectOnScreen(): Rect {
        val location = locationOnScreen()
        return Rect(location.x, location.y, location.x + measuredWidth, location.y + measuredHeight)
    }
}