package x.android.commons.constant

import CommonsColor
import x.android.commons.context.Global.resource

object ColorX {

    val TRANSPARENT = color(CommonsColor.transparent)
    val WHITE = color(CommonsColor.white)
    val BLACK = color(CommonsColor.black)
    val BLACK_15 = color(CommonsColor.black_percent_15)

    fun color(resId: Int) = resource.getColor(resId)
}