package io.github.hellogoogle2000.android.commons.context

import android.os.Build

object BuildX {

    fun isApiLevelAbove(apiLevel: Int): Boolean {
        return Build.VERSION.SDK_INT >= apiLevel
    }

    fun isApiLevelBelow(apiLevel: Int): Boolean {
        return Build.VERSION.SDK_INT < apiLevel
    }
}