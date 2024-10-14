package x.android.commons.view.webview

interface XWebViewCallback {

    fun onWebViewTimeout() {}

    fun onDocumentStartLoading() {}

    fun onDocumentFinishLoading() {}

    fun onDataLoaded() {}
}