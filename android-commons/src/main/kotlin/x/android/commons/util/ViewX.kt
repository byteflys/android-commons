package x.android.commons.util

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT

object ViewX {

    fun View.setLayoutParams(block: ViewGroup.LayoutParams.() -> Unit) {
        val layoutParams = layoutParams ?: ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        block.invoke(layoutParams)
        this.layoutParams = layoutParams
    }
}