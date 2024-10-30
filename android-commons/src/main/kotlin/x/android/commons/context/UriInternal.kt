package x.android.commons.context

import android.net.Uri
import android.provider.OpenableColumns
import x.android.commons.context.Global.app
import x.android.commons.context.UriX.getExtensionName
import x.kotlin.commons.string.UUID
import kotlin.io.path.Path
import kotlin.io.path.name

internal object UriInternal {

    // scheme must be content
    fun Uri.queryNameByContent(): String {
        val cursor = app.contentResolver.query(this, null, null, null, null, null)
        cursor ?: return ""
        cursor.moveToFirst()
        val index = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        val displayName = cursor.getString(index)
        cursor.close()
        return displayName.orEmpty()
    }

    // path must not be blank
    fun Uri.queryNameByPath(): String {
        val pathName = Path(path.orEmpty()).name
        if (pathName.isBlank())
            return ""
        val ext = ApacheFileNameUtils.getExtension(pathName)
        if (ext.isBlank()) {
            val expectExt = getExtensionName()
            return "$pathName.$expectExt"
        }
        return pathName
    }

    // cannot get name from other ways
    fun Uri.getAlternativeName(): String {
        val name = UUID.short()
        val ext = getExtensionName()
        if (ext.isEmpty()) {
            return name
        }
        return "$name.$ext"
    }
}