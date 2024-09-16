package x.android.commons.context

import android.app.Activity
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.blankj.utilcode.util.ActivityUtils
import x.android.commons.context.BuildX.isApiLevelAbove

object ActivityX {

    fun AppCompatActivity.isFront(): Boolean {
        return lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)
    }

    fun getFrontActivity(): Activity {
        val activities = ActivityUtils.getActivityList()
        val front = activities.find { it is AppCompatActivity && it.isFront() }
        return front ?: ActivityUtils.getTopActivity()
    }

    fun Activity.immersive() {
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

    fun Activity.float(
        width: Int = WRAP_CONTENT,
        height: Int = WRAP_CONTENT,
        gravity: Int = Gravity.CENTER,
        x: Int = 0,
        y: Int = 0
    ) {
        val lp = window.attributes
        lp.width = width
        lp.height = height
        lp.gravity = gravity
        lp.x = x
        lp.y = y
        window.attributes = lp
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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