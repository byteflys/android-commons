package x.android.commons.view

import android.util.Log
import android.webkit.JavascriptInterface
import x.android.commons.ui.ToastX

// delegate javascript console to android logcat
object XWebViewDebugger {

    @JavascriptInterface
    fun log(obj: String?) {
        Log.d(XWebViewDebugger::class.simpleName, obj.toString())
    }

    @JavascriptInterface
    fun error(obj: String?) {
        Log.e(XWebViewDebugger::class.simpleName, obj.toString())
    }

    @JavascriptInterface
    fun alert(obj: String?) {
        ToastX.show(obj.orEmpty())
    }
}