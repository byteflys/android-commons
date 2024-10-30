package x.android.commons.context

import ApacheFileUtils
import android.content.ContentResolver
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.webkit.MimeTypeMap
import x.android.commons.context.Global.app
import x.android.commons.context.UriInternal.getAlternativeName
import x.android.commons.context.UriInternal.queryNameByContent
import x.android.commons.context.UriInternal.queryNameByPath
import java.io.File

object UriX {

    const val SCHEME_HTTP = "http"
    const val SCHEME_HTTPS = "https"
    const val FILE_URI = "file:///"
    const val FILE_ASSET_URI = "file:///android_asset/"

    fun String?.isWebsiteUri(): Boolean {
        if (isNullOrEmpty()) {
            return false
        }
        return startsWith(SCHEME_HTTP, true) || startsWith(SCHEME_HTTPS, true)
    }

    fun String?.isFileUri(): Boolean {
        if (isNullOrEmpty()) {
            return false
        }
        return startsWith(FILE_URI, true)
    }

    fun String?.isWebsiteOrFileUri(): Boolean {
        return isWebsiteUri() || isFileUri()
    }

    fun uriFromAsset(fileName: String): Uri {
        return Uri.parse("$FILE_ASSET_URI$fileName")
    }

    fun Uri.getMimeType(): String {
        return MimeTypeMap.getSingleton()
            .getMimeTypeFromExtension(getExtensionName())
            .orEmpty()
    }

    fun Uri.getExtensionName(): String {
        val typeMap = MimeTypeMap.getSingleton()
        when (scheme) {
            ContentResolver.SCHEME_FILE -> {
                val url = Uri.fromFile(File(path)).toString()
                return MimeTypeMap.getFileExtensionFromUrl(url)
            }
            ContentResolver.SCHEME_CONTENT -> {
                val type = app.contentResolver.getType(this)
                return typeMap.getExtensionFromMimeType(type).orEmpty()
            }
            ContentResolver.SCHEME_ANDROID_RESOURCE,
            SCHEME_HTTP,
            SCHEME_HTTPS -> {
                val retriever = MediaMetadataRetriever()
                retriever.setDataSource(app, this)
                val type = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE)
                return typeMap.getExtensionFromMimeType(type).orEmpty()
            }
            else -> return ""
        }
    }

    fun Uri.getFileName(): String {
        return when (scheme) {
            ContentResolver.SCHEME_FILE -> {
                return File(path).name
            }
            ContentResolver.SCHEME_CONTENT -> {
                val contentName = queryNameByContent()
                if (contentName.isNotBlank()) {
                    return contentName
                }
                val pathName = queryNameByPath()
                if (pathName.isNotBlank()) {
                    return pathName
                }
                return getAlternativeName()
            }
            else -> return getAlternativeName()
        }
    }

    fun Uri.copyToFile(dst: String) {
        val input = app.contentResolver.openInputStream(this)
        ApacheFileUtils.copyToFile(input, File(dst))
    }
}