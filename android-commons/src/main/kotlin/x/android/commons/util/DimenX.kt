package x.android.commons.util

import x.android.commons.context.Global

object DimenX {

    fun dp2px(dp: Number): Int {
        val scale = Global.app.resources.displayMetrics.density
        return (dp.toFloat() * scale + 0.5f).toInt()
    }

    fun px2dp(px: Number): Int {
        val scale = Global.app.resources.displayMetrics.density
        return (px.toFloat() / scale + 0.5f).toInt()
    }

    fun dp2PxFloat(dp: Number): Float {
        val scale = Global.app.resources.displayMetrics.density
        return dp.toFloat() * scale + 0.5F
    }

    fun px2DpFloat(px: Number): Float {
        val scale = Global.app.resources.displayMetrics.density
        return px.toFloat() / scale + 0.5F
    }
}