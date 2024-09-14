package io.github.hellogoogle2000.android.commons.component

import android.app.Application
import android.os.Handler
import android.os.Looper
import io.github.hellogoogle2000.android.commons.constant.Global

class CommonApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initApplication()
    }

    private fun initApplication() {
        Global.TAG = packageName.split(".").last()
        Global.application = this
        Global.handler = Handler(Looper.getMainLooper())
    }
}