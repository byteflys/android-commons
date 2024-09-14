package io.github.hellogoogle2000.android.commons.view

interface ByteFlyWebViewCallback {

    fun onWebViewTimeout() {}

    fun onDocumentStartLoading() {}

    fun onDocumentFinishLoading() {}
}