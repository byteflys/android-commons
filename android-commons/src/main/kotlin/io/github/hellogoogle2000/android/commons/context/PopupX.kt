package io.github.hellogoogle2000.android.commons.context

import android.view.View
import android.widget.PopupWindow

object PopupX {

    fun <T : PopupWindow> T.fullscreen() = apply {
        val flag = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
        contentView.setSystemUiVisibility(flag)
    }
}