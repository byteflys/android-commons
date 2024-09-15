package io.github.hellogoogle2000.android.commons.context

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.os.Build
import android.view.View
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.blankj.utilcode.util.ActivityUtils
import io.github.hellogoogle2000.android.commons.context.BuildX.isApiLevelAbove

object ActivityX {

    fun AppCompatActivity.isFront(): Boolean {
        return lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)
    }

    fun getFrontActivity(): Activity {
        val activities = ActivityUtils.getActivityList()
        val front = activities.find { it is AppCompatActivity && it.isFront() }
        return front ?: ActivityUtils.getTopActivity()
    }

    fun View.getActivity(): Activity {
        val activity = context as? Activity
        return activity ?: throw RuntimeException("ui context required")
    }

    fun Context.getRootView(): View {
        val activity = this as? Activity
        activity ?: throw RuntimeException("ui context required")
        return activity.findViewById(android.R.id.content)
    }

    fun View.getRootView() = getActivity().getRootView()

    fun Activity.fullscreen() {
        if (isApiLevelAbove(Build.VERSION_CODES.R)) {
            window.decorView.windowInsetsController
            window.decorView.windowInsetsController?.hide(WindowInsets.Type.navigationBars())
            window.decorView.windowInsetsController?.hide(WindowInsets.Type.statusBars())
            window.setDecorFitsSystemWindows(false)
            return
        }
        window.statusBarColor = Color.TRANSPARENT
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    fun AppCompatActivity.isNavBarVisible(): Boolean {
        val size = Point()
        val realSize = Point()
        val defaultDisplay = this.windowManager.defaultDisplay
        defaultDisplay.getSize(size)
        defaultDisplay.getRealSize(realSize)
        return realSize.y != size.y
    }
}