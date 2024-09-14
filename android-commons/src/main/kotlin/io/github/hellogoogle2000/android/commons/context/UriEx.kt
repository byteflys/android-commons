package io.github.hellogoogle2000.android.commons.context

import android.content.ContentResolver
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.webkit.MimeTypeMap
import io.github.hellogoogle2000.android.commons.constant.Global
import java.io.File

object UriEx {

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

    fun Uri.getExtensionName(): String {
        val context = Global.application
        val typeMap = MimeTypeMap.getSingleton()
        when (scheme) {
            ContentResolver.SCHEME_FILE -> {
                val url = Uri.fromFile(File(path)).toString()
                return MimeTypeMap.getFileExtensionFromUrl(url)
            }
            ContentResolver.SCHEME_CONTENT -> {
                val type = context.contentResolver.getType(this)
                return typeMap.getExtensionFromMimeType(type).orEmpty()
            }
            ContentResolver.SCHEME_ANDROID_RESOURCE -> {
                val retriever = MediaMetadataRetriever()
                retriever.setDataSource(context, this)
                val type = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE)
                return typeMap.getExtensionFromMimeType(type).orEmpty()
            }
            SCHEME_HTTP,
            SCHEME_HTTPS -> return ""
            else -> return ""
        }
    }

    fun Uri.getMimeType(): String {
        return MimeTypeMap.getSingleton()
            .getMimeTypeFromExtension(getExtensionName())
            .orEmpty()
    }
}