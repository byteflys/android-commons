package x.android.commons.view.maxsize

import android.content.Context
import android.util.AttributeSet
import x.android.commons.R
import x.android.commons.ui.ViewLocator.getApplicationSize
import x.android.commons.util.DimenX.dp2px
import x.android.commons.view.webview.XWebView

open class MaxSizeWebView : XWebView {

    private var basicWidth = 0f
    private var basicHeight = 0f
    private var maxScreenRatioX = 0f
    private var maxScreenRatioY = 0f

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, android.R.attr.webViewStyle)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        parseAttribute(attrs)
    }

    private fun parseAttribute(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaxSizeWebView)
        if (typedArray.hasValue(R.styleable.MaxSizeWebView_basicWidth)) {
            basicWidth = typedArray.getDimension(R.styleable.MaxSizeWebView_basicWidth, 0f)
        }
        if (typedArray.hasValue(R.styleable.MaxSizeWebView_basicHeight)) {
            basicHeight = typedArray.getDimension(R.styleable.MaxSizeWebView_basicHeight, 0f)
        }
        if (typedArray.hasValue(R.styleable.MaxSizeWebView_maxScreenRatioX)) {
            maxScreenRatioX = typedArray.getFloat(R.styleable.MaxSizeWebView_maxScreenRatioX, 0f)
        }
        if (typedArray.hasValue(R.styleable.MaxSizeWebView_maxScreenRatioY)) {
            maxScreenRatioY = typedArray.getFloat(R.styleable.MaxSizeWebView_maxScreenRatioY, 0f)
        }
        typedArray.recycle()
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)
        val minWidth = dp2px(100)
        val minHeight = dp2px(24)
        var availableWidth = context.getApplicationSize().width
        var availableHeight = context.getApplicationSize().height
        if (maxScreenRatioX > 0) {
            availableWidth = (availableWidth * maxScreenRatioX - basicWidth).toInt()
            if (availableWidth <= 0) {
                setMeasuredDimension(minWidth, measuredHeight)
            } else if (measuredWidth > availableWidth) {
                setMeasuredDimension(availableWidth, measuredHeight)
            }
        }
        if (maxScreenRatioY > 0) {
            availableHeight = (availableHeight * maxScreenRatioY - basicHeight).toInt()
            if (availableHeight <= 0) {
                setMeasuredDimension(measuredWidth, minHeight)
            } else if (measuredHeight > availableHeight) {
                setMeasuredDimension(measuredWidth, availableHeight)
            }
        }
    }
}