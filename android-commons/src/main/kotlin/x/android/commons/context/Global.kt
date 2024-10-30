package x.android.commons.context

import android.app.Application
import android.content.res.Resources
import android.os.Handler

object Global {

    lateinit var TAG: String

    lateinit var app: Application

    lateinit var handler: Handler

    lateinit var resource: Resources
}