package x.android.commons.view

import SingleArgCallbackFunc
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.net.http.SslError
import android.util.AttributeSet
import android.util.Log
import android.webkit.PermissionRequest
import android.webkit.SslErrorHandler
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import x.android.commons.context.ContextX.open
import x.android.commons.context.UriX.isWebsiteOrFileUri
import io.github.hellogoogle2000.kotlin.commons.serialize.JSONEx.fromJsonOrNull
import io.github.hellogoogle2000.kotlin.commons.serialize.JSONEx.toJson

@SuppressLint("JavascriptInterface")
open class ByteFlyWebView : WebView {

    private var callback: ByteFlyWebViewCallback? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : this(context, attributeSet, defStyleAttr, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attributeSet, defStyleAttr, defStyleRes) {
        initWebSettings()
        initWebViewClient()
        initWebChromeClient()
        initJavascriptContext()
        setBackgroundColor(Color.TRANSPARENT)
    }

    private fun initWebSettings() {
        setWebContentsDebuggingEnabled(true)
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
    }

    private fun initWebViewClient() {
        webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(webView: WebView, request: WebResourceRequest): Boolean {
                val url = request.url.toString()
                return shouldInterceptUrl(url)
            }

            override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? {
                val url = request.url.toString()
                val delegate = shouldInterceptRequest(url, request)
                return delegate ?: super.shouldInterceptRequest(view, request)
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                val url = request.url.toString()
                Log.e(ByteFlyWebView::class.simpleName, "WebViewError $url ${error?.errorCode} ${error?.description}")
                if (!request.isForMainFrame) {
                    return
                }
                when (error?.errorCode) {
                    ERROR_TIMEOUT -> onWebViewTimeout(url)
                }
            }

            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                handler.proceed()
            }
        }
    }

    private fun initWebChromeClient() {
        webChromeClient = object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest) {
                request.grant(request.resources)
            }
        }
    }

    private fun initJavascriptContext() {
        registerNativeObject("debugger", ByteFlyWebViewDebugger)
    }

    // false means continue
    // otherwise abort to open
    open fun shouldInterceptUrl(url: String): Boolean {
        if (url.isWebsiteOrFileUri()) {
            return false
        }
        context.open(url)
        return true
    }

    // intercept specified request
    // and return a delegated response
    // null means do not intercept
    open fun shouldInterceptRequest(url: String, request: WebResourceRequest): WebResourceResponse? {
        return null
    }

    open fun onWebViewTimeout(url: String) {
        callback?.onWebViewTimeout()
    }

    open fun onDocumentFinishLoading() {
        callback?.onDocumentFinishLoading()
    }

    override fun loadUrl(url: String) {
        super.loadUrl(url)
        callback?.onDocumentStartLoading()
    }

    fun openAsset(assetPath: String) = apply {
        loadUrl("file:///android_asset/$assetPath")
    }

    fun openWebpage(url: String) = apply {
        loadUrl(url)
    }

    fun registerNativeObject(name: String, nativeObject: Any) = apply {
        addJavascriptInterface(nativeObject, name)
    }

    fun executeJavascriptSentence(
        funcName: String,
        argsObject: Any = Unit,
        onEvaluateResult: SingleArgCallbackFunc<String?> = {}
    ) = apply {
        var argsJson = argsObject.toJson()
        val callback = ValueCallback<String?> {
            if (it == "null") {
                onEvaluateResult(null)
            }
            onEvaluateResult(it)
        }
        post {
            evaluateJavascript("javascript:$funcName($argsJson)", callback)
        }
    }

    fun <T> executeJavascriptSentence(
        funcName: String,
        argsObject: Any = Unit,
        returnType: Class<T>,
        returnObjectHandler: SingleArgCallbackFunc<T> = {}
    ) = executeJavascriptSentence(funcName, argsObject) { result ->
        val returnObject = result.fromJsonOrNull(returnType)
        returnObject?.let { returnObjectHandler(it) }
    }

    fun setWebViewCallback(callback: ByteFlyWebViewCallback) = apply {
        this.callback = callback
    }
}