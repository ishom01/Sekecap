package id.ishom.sekecap_draw.testing_widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import id.ishom.sekecap_draw.height
import id.ishom.sekecap_draw.minHorizontal
import id.ishom.sekecap_draw.minVertical
import id.ishom.sekecap_draw.width

class TestingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    var mCurrentView: TestingCanvas? = null
    var mRect = RectF(0f, 0f, 0f, 0f)

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mRect.left = event.x
                mRect.top = event.y
                mRect.bottom = event.y
                mRect.right = event.x
                mCurrentView = TestingCanvas(context)
                mCurrentView?.drawRectangle(mRect)
                Log.e("Debug Canvas", "On Down ${event.x}, ${event.y}")
                Log.e("Debug Canvas", "On Down ${mCurrentView == null}")
            }
            MotionEvent.ACTION_MOVE -> {
                mRect.bottom = event.y
                mRect.right = event.x
                mCurrentView?.drawRectangle(mRect)
                Log.e("Debug Canvas", "On Move ${event.x}, ${event.y}")
                Log.e("Debug Canvas", "On Move ${mCurrentView == null}")
            }
            MotionEvent.ACTION_UP -> {
                mRect.bottom = event.y
                mRect.right = event.x
                mCurrentView?.drawRectangle(mRect)
                addCanvasView(mCurrentView!!)
                Log.e("Debug Canvas", "On UP ${event.x}, ${event.y}")
                Log.e("Debug Canvas", "On UP ${mCurrentView == null}")
            }
        }
        return true
    }

    private fun addCanvasView(view: View) {
        val params = LayoutParams(mRect.width, mRect.height)
        params.topMargin = mRect.minVertical
        params.leftMargin = mRect.minHorizontal
        view.layoutParams = params
        addView(view)
    }

    fun getBitmap(): Bitmap {
        val bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.WHITE)
        draw(canvas)
        return bitmap
    }
}