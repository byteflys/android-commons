package x.android.commons.view.richtext

import android.content.Context
import android.util.AttributeSet
import x.android.commons.util.ViewX.setLayoutParams
import x.android.commons.view.maxsize.MaxSizeWebView

class RichTextView : MaxSizeWebView {

    private var fitContentHeight = false

    private var pageScale = 1f

    private val standardDestiny = 1.2f

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {
        val destiny = context.resources.displayMetrics.density
        pageScale = destiny / standardDestiny
        settings.loadWithOverviewMode = false
        setInitialScale((pageScale * 100).toInt())
    }

    fun fitContentHeight(autoFit: Boolean) = apply { this.fitContentHeight = autoFit }

    fun setRichText(richText: String) {
        val builder = StringBuilder()
        builder.append("<html>").append("\n")
        builder.append("<head>").append("\n")
        builder.append("  <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />").append("\n")
        builder.append("</head>").append("\n")
        builder.append("  <style>").append("\n")
        builder.append(createStandardStyle()).append("\n")
        builder.append("  </style>").append("\n")
        builder.append("<body>").append("\n")
        builder.append("  <div id='content'>").append("\n")
        builder.append("    $richText").append("\n")
        builder.append("  </div>").append("\n")
        builder.append("</body>").append("\n")
        builder.append("</html>")
        val htmlText = builder.toString()
        openHtmlText(htmlText)
    }

    override fun onDataLoaded() {
        if (fitContentHeight) {
            fitContentHeight()
        }
    }

    private fun fitContentHeight() {
        executeJavascriptVariable("document.getElementById('content').scrollHeight", Int::class.java) {
            val htmlHeight = it ?: return@executeJavascriptVariable
            val scaledHeight = (htmlHeight * pageScale).toInt()
            setLayoutParams { height = scaledHeight }
        }
    }

    private fun createStandardStyle(): String {
        return """
            :root {
              font-size:16px;
            }
            p {
              font-size:1rem;
              line-height:2;
              margin:0;
            }
        """
    }
}