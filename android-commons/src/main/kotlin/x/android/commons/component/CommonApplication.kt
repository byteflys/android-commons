package x.android.commons.component

import android.app.Application
import android.os.Handler
import android.os.Looper
import x.android.commons.context.Global

class CommonApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initApplication()
    }

    private fun initApplication() {
        Global.TAG = packageName.split(".").last()
        Global.app = this
        Global.handler = Handler(Looper.getMainLooper())
        Global.resource = resources
    }
}