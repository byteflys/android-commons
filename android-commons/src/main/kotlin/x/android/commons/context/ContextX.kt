package x.android.commons.context

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import x.android.commons.ui.ToastX

object ContextX {

    fun Context.open(uri: String) {
        try {
            val intent = Intent(ACTION_VIEW, Uri.parse(uri))
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } catch (e: Throwable) {
            ToastX.show("No Target Found")
        }
    }
}