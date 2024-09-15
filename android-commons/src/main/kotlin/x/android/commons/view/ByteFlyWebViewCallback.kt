package x.android.commons.view

interface ByteFlyWebViewCallback {

    fun onWebViewTimeout() {}

    fun onDocumentStartLoading() {}

    fun onDocumentFinishLoading() {}
}