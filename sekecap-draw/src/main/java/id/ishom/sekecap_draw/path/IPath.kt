package id.ishom.sekecap_draw.path

import android.graphics.*

abstract class IPath: Path() {
    var drawMode: DrawMode? = null
    var isSelected = false
        set(value) {
            field = value
            if (field) {
                drawFrame()
            }
            else {
                removeFrame()
            }
        }

    var mRect = RectF(0f,0f, 0f, 0f)

    var mFramePaint = Paint().apply {
        color = Color.RED
        strokeWidth = 2f
        style = Paint.Style.STROKE
    }

    abstract fun draw()

    fun onTouch(point: PointF) {
        mRect.left = point.x
        mRect.top = point.y
    }

    fun onMove(point: PointF) {
        mRect.right = point.x
        mRect.bottom = point.y
        rewind()
        draw()
    }

    fun onDeTouch(point: PointF) {
        mRect.right = point.x
        mRect.bottom = point.y
        draw()
    }

    private fun drawFrame() {
        addRect(mRect, Direction.CW)
    }

    private fun removeFrame() {
    }


    fun isInSelectedZone(point: PointF): Boolean {
        return point.x in mRect.left..mRect.right && point.y in mRect.top..mRect.bottom
    }
}