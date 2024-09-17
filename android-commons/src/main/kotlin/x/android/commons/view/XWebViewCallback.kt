package x.android.commons.view

interface XWebViewCallback {

    fun onWebViewTimeout() {}

    fun onDocumentStartLoading() {}

    fun onDocumentFinishLoading() {}
}