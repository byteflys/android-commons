package x.android.commons.ui

import android.view.Gravity

data class Location(
    var x: Int = 0,
    var y: Int = 0,
    var dx: Int = 0,
    var dy: Int = 0,
    var gravity: Int = Gravity.NO_GRAVITY
)