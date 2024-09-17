package x.android.commons.ui

enum class DockPoint {

    TOP_LEFT, TOP_CENTER, TOP_RIGHT,

    CENTER_LEFT, CENTER, CENTER_RIGHT,

    BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT;

    fun reverse(
        reverseX: Boolean = false,
        reverseY: Boolean = false
    ): DockPoint {
        var reversed = this
        if (reverseX) {
            reversed = reversed.reverseX()
        }
        if (reverseY) {
            reversed = reversed.reverseY()
        }
        return reversed
    }

    private fun reverseX(): DockPoint {
        return when (this) {
            TOP_LEFT -> TOP_RIGHT
            TOP_CENTER -> TOP_CENTER
            TOP_RIGHT -> TOP_LEFT
            CENTER_LEFT -> CENTER_RIGHT
            CENTER -> CENTER
            CENTER_RIGHT -> CENTER_LEFT
            BOTTOM_LEFT -> BOTTOM_RIGHT
            BOTTOM_CENTER -> BOTTOM_CENTER
            BOTTOM_RIGHT -> BOTTOM_LEFT
        }
    }

    private fun reverseY(): DockPoint {
        return when (this) {
            TOP_LEFT -> BOTTOM_LEFT
            TOP_CENTER -> BOTTOM_CENTER
            TOP_RIGHT -> BOTTOM_RIGHT
            CENTER_LEFT -> CENTER_LEFT
            CENTER -> CENTER
            CENTER_RIGHT -> CENTER_RIGHT
            BOTTOM_LEFT -> TOP_LEFT
            BOTTOM_CENTER -> TOP_CENTER
            BOTTOM_RIGHT -> TOP_RIGHT
        }
    }
}