package io.github.hellogoogle2000.android.commons.view

import android.util.Log
import android.webkit.JavascriptInterface
import io.github.hellogoogle2000.android.commons.dialog.ToastX

// delegate javascript console to android logcat
object ByteFlyWebViewDebugger {

    @JavascriptInterface
    fun log(obj: String?) {
        Log.d(ByteFlyWebViewDebugger::class.simpleName, obj.toString())
    }

    @JavascriptInterface
    fun error(obj: String?) {
        Log.e(ByteFlyWebViewDebugger::class.simpleName, obj.toString())
    }

    @JavascriptInterface
    fun alert(obj: String?) {
        ToastX.show(obj.orEmpty())
    }
}