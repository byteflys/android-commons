package x.android.commons.ui

import android.content.Context
import android.graphics.Rect
import android.view.Gravity
import android.view.View
import android.widget.PopupWindow
import x.android.commons.context.ActivityX
import x.android.commons.ui.ViewLocator.getActivityDecorView
import x.android.commons.ui.ViewLocator.rectInActivity
import x.android.commons.ui.ViewLocator.rectInWindow

object PopupLocator {

    fun <T : PopupWindow> T.getWindowLocation(
        anchorView: View,
        dstDockPoint: DockPoint = DockPoint.BOTTOM_CENTER,
        srcDockPoint: DockPoint = DockPoint.TOP_CENTER,
        dx: Int = 0,
        dy: Int = 0,
        windowContext: Context = ActivityX.getFrontActivity()
    ): WindowLocation {
        val rectInActivity = anchorView.rectInActivity()
        return getWindowLocation(rectInActivity, dstDockPoint, srcDockPoint, dx, dy, windowContext)
    }

    fun <T : PopupWindow> T.getWindowLocation(
        dstRectInWindow: Rect = Rect(),
        dstDockPoint: DockPoint = DockPoint.BOTTOM_CENTER,
        srcDockPoint: DockPoint = DockPoint.TOP_CENTER,
        dx: Int = 0,
        dy: Int = 0,
        windowContext: Context = ActivityX.getFrontActivity()
    ): WindowLocation {
        val parent = windowContext.getActivityDecorView()
        val parentRect = parent.rectInWindow()
        val gravityX = getGravityX(srcDockPoint)
        val gravityY = getGravityY(srcDockPoint)
        val gravity = gravityX or gravityY
        val x = getOffsetX(dstRectInWindow, parentRect, dstDockPoint, gravityX)
        val y = getOffsetY(dstRectInWindow, parentRect, dstDockPoint, gravityY)
        val windowLocation = WindowLocation(x, y, dx, dy, gravity)
        return windowLocation
    }

    fun <T : PopupWindow> T.show(
        windowLocation: WindowLocation,
        windowContext: Context = ActivityX.getFrontActivity()
    ) = apply {
        val parent = windowContext.getActivityDecorView()
        showAtLocation(
            parent,
            windowLocation.gravity,
            windowLocation.x + windowLocation.dx,
            windowLocation.y + windowLocation.dy
        )
    }

    fun <T : PopupWindow> T.update(
        windowLocation: WindowLocation,
        width: Int = getWidth(),
        height: Int = getHeight()
    ) = apply {
        update(
            windowLocation.x + windowLocation.dx,
            windowLocation.y + windowLocation.dy,
            width, height
        )
    }

    private fun getGravityX(srcDockPoint: DockPoint): Int {
        return when (srcDockPoint) {
            DockPoint.TOP_LEFT,
            DockPoint.CENTER_LEFT,
            DockPoint.BOTTOM_LEFT -> Gravity.LEFT
            DockPoint.TOP_CENTER,
            DockPoint.CENTER,
            DockPoint.BOTTOM_CENTER -> Gravity.CENTER_HORIZONTAL
            DockPoint.TOP_RIGHT,
            DockPoint.CENTER_RIGHT,
            DockPoint.BOTTOM_RIGHT -> Gravity.RIGHT
        }
    }

    private fun getGravityY(srcDockPoint: DockPoint): Int {
        return when (srcDockPoint) {
            DockPoint.TOP_LEFT,
            DockPoint.TOP_CENTER,
            DockPoint.TOP_RIGHT -> Gravity.TOP
            DockPoint.CENTER_LEFT,
            DockPoint.CENTER,
            DockPoint.CENTER_RIGHT -> Gravity.CENTER_VERTICAL
            DockPoint.BOTTOM_LEFT,
            DockPoint.BOTTOM_CENTER,
            DockPoint.BOTTOM_RIGHT -> Gravity.BOTTOM
        }
    }

    private fun getOffsetX(
        dstRect: Rect,
        parentRect: Rect,
        dstDockPoint: DockPoint,
        gravityX: Int
    ): Int {
        when (gravityX) {
            Gravity.LEFT -> {
                return when (dstDockPoint) {
                    DockPoint.TOP_LEFT,
                    DockPoint.CENTER_LEFT,
                    DockPoint.BOTTOM_LEFT -> dstRect.left
                    DockPoint.TOP_CENTER,
                    DockPoint.CENTER,
                    DockPoint.BOTTOM_CENTER -> dstRect.centerX()
                    DockPoint.TOP_RIGHT,
                    DockPoint.CENTER_RIGHT,
                    DockPoint.BOTTOM_RIGHT -> dstRect.right
                }
            }
            Gravity.CENTER_HORIZONTAL -> {
                return when (dstDockPoint) {
                    DockPoint.TOP_LEFT,
                    DockPoint.CENTER_LEFT,
                    DockPoint.BOTTOM_LEFT -> dstRect.left - parentRect.centerX()
                    DockPoint.TOP_CENTER,
                    DockPoint.CENTER,
                    DockPoint.BOTTOM_CENTER -> dstRect.centerX() - parentRect.centerX()
                    DockPoint.TOP_RIGHT,
                    DockPoint.CENTER_RIGHT,
                    DockPoint.BOTTOM_RIGHT -> dstRect.right - parentRect.centerX()
                }
            }
            Gravity.RIGHT -> {
                return when (dstDockPoint) {
                    DockPoint.TOP_LEFT,
                    DockPoint.CENTER_LEFT,
                    DockPoint.BOTTOM_LEFT -> parentRect.right - dstRect.left
                    DockPoint.TOP_CENTER,
                    DockPoint.CENTER,
                    DockPoint.BOTTOM_CENTER -> parentRect.right - dstRect.centerX()
                    DockPoint.TOP_RIGHT,
                    DockPoint.CENTER_RIGHT,
                    DockPoint.BOTTOM_RIGHT -> parentRect.right - dstRect.right
                }
            }
        }
        return 0
    }

    private fun getOffsetY(
        dstRect: Rect,
        parentRect: Rect,
        dstDockPoint: DockPoint,
        gravityY: Int
    ): Int {
        when (gravityY) {
            Gravity.TOP -> {
                return when (dstDockPoint) {
                    DockPoint.TOP_LEFT,
                    DockPoint.TOP_CENTER,
                    DockPoint.TOP_RIGHT -> dstRect.top
                    DockPoint.CENTER_LEFT,
                    DockPoint.CENTER,
                    DockPoint.CENTER_RIGHT -> dstRect.centerY()
                    DockPoint.BOTTOM_LEFT,
                    DockPoint.BOTTOM_CENTER,
                    DockPoint.BOTTOM_RIGHT -> dstRect.bottom
                }
            }
            Gravity.CENTER_VERTICAL -> {
                return when (dstDockPoint) {
                    DockPoint.TOP_LEFT,
                    DockPoint.TOP_CENTER,
                    DockPoint.TOP_RIGHT -> dstRect.top - parentRect.centerY()
                    DockPoint.CENTER_LEFT,
                    DockPoint.CENTER,
                    DockPoint.CENTER_RIGHT -> dstRect.centerY() - parentRect.centerY()
                    DockPoint.BOTTOM_LEFT,
                    DockPoint.BOTTOM_CENTER,
                    DockPoint.BOTTOM_RIGHT -> dstRect.bottom - parentRect.centerY()
                }
            }
            Gravity.BOTTOM -> {
                return when (dstDockPoint) {
                    DockPoint.TOP_LEFT,
                    DockPoint.TOP_CENTER,
                    DockPoint.TOP_RIGHT -> parentRect.bottom - dstRect.top
                    DockPoint.CENTER_LEFT,
                    DockPoint.CENTER,
                    DockPoint.CENTER_RIGHT -> parentRect.bottom - dstRect.centerY()
                    DockPoint.BOTTOM_LEFT,
                    DockPoint.BOTTOM_CENTER,
                    DockPoint.BOTTOM_RIGHT -> parentRect.bottom - dstRect.bottom
                }
            }
        }
        return 0
    }
}