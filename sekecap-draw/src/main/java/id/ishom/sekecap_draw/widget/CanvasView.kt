package id.ishom.sekecap_draw.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout
import id.ishom.sekecap_draw.height
import id.ishom.sekecap_draw.width

class CanvasView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    var mRect = RectF(0f, 0f, 0f, 0f)

    var mCurrDrawView: ShapeView? = null
    var mSelectedView: ShapeView? = null

    var mDrawViews = arrayListOf<ShapeView?>()

    init {
        setWillNotDraw(false)
    }

    fun onDown(x: Float, y: Float) {
        mRect.apply {
            left = x
            top = y
            right = x
            bottom = y
        }
        when {
            mSelectedView != null -> mSelectedView?.onBeforeTransition()
        }
        invalidate()
    }

    fun onMove(x: Float, y: Float) {
        mRect.apply {
            right = x
            bottom = y
        }
        when {
            mSelectedView != null -> {
                mSelectedView?.onTransition(mRect)
                Log.e("Debug Canvas", "Try to drag")
            }
            !isClicked && mCurrDrawView == null -> {
                mCurrDrawView = ShapeView(context, mRect)
                addView(mCurrDrawView)
            }
            else -> {
                mCurrDrawView?.onChangeSize(mRect)
            }
        }
        invalidate()
    }

    fun onUp(x: Float, y: Float) {
        mRect.apply {
            right = x
            bottom = y
        }
        when {
            isClicked -> {
                findSelectedChildView()
            }
            mSelectedView != null -> {
                mCurrDrawView?.onTransition(mRect)
            }
            else -> {
                mCurrDrawView?.onChangeSize(mRect)
                mDrawViews.add(mCurrDrawView)
                mCurrDrawView = null
                Log.e("Debug Canvas", "=====================}")
                Log.e("Debug Canvas", "On Last Draw ${mRect}")
            }
        }
        invalidate()
    }

    var clickThreshold = 5f
    private val isClicked: Boolean get() {
        return mRect.width <= clickThreshold && mRect.height <= clickThreshold
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                onDown(event.x, event.y)
                Log.e("Debug Canvas", "On Down ${event.x}, ${event.y}")
            }
            MotionEvent.ACTION_MOVE -> {
                onMove(event.x, event.y)
//                Log.e("Debug Canvas", "On Move ${event.x}, ${event.y}")
            }
            MotionEvent.ACTION_UP -> {
                onUp(event.x, event.y)
                Log.e("Debug Canvas", "On UP ${event.x}, ${event.y}")
            }
        }
        return true
    }

    private fun findSelectedChildView() {
        mSelectedView?.clearSelected()
        Log.e("Debug Canvas", "On Canvas count $childCount")
        for (index in mDrawViews.lastIndex downTo 0) {
            val drawView = mDrawViews.getOrNull(index)
            if (drawView?.isClicked(PointF(mRect.right, mRect.bottom)) == true) {
                mSelectedView = drawView
                mSelectedView?.drawState()
                return
            }
        }
        mSelectedView = null
    }

    fun getBitmap(): Bitmap {
        val bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.WHITE)
        draw(canvas)
        return bitmap
    }
}