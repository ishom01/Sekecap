package id.ishom.sekecap_draw.testing_widget

import android.content.Context
import android.graphics.*
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import id.ishom.sekecap_draw.path.DrawMode

class Canvas @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    // start position
    private var mStartX = 0f
    private var mStartY = 0f
    private var mCurX = 0f
    private var mCurY = 0f

    private var paint = Paint().apply {
        color = Color.GREEN
        alpha = 100
        strokeWidth = 2f
        style = Paint.Style.FILL
    }

    var drawMode: DrawMode? = null

    // recent Point
    var mPath = arrayListOf<DrawPath>()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (path in mPath) {
            canvas.drawPath(path, paint)
        }
    }

    fun getBitmap(): Bitmap {
        val bitmap = Bitmap.createBitmap(400, 200, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.WHITE)
        draw(canvas)
        return bitmap
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (drawMode == null) return false
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mStartX = event.x
                mStartY = event.y
            }
            MotionEvent.ACTION_MOVE -> {

            }
            MotionEvent.ACTION_UP -> {

            }
        }
        return true
    }

    private fun touchDown(x: Float, y: Float) {
        mCurX = x
        mCurY = y
        val path = Path()
    }

    private fun touchMove(x: Float, y: Float) {
        mCurX = x
        mCurY = y
    }

    private fun touchUp() {

    }
}