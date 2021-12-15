package id.ishom.sekecap_draw.widget

import android.content.Context
import android.graphics.*
import android.graphics.Canvas
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import id.ishom.sekecap_draw.path.Position
import kotlin.math.roundToInt

class ShapeView @JvmOverloads constructor(
    context: Context, private val mRectF: RectF, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var mCurrentParams: FrameLayout.LayoutParams? = null

    var oldPos = Position()
    var curPos = Position()

    var paint = Paint().apply {
        color = Color.GREEN
        strokeWidth = 2f
        style = Paint.Style.FILL
    }

    init {
        oldPos.apply {
            left = mRectF.left
            top = mRectF.top
            right = mRectF.right
            bottom = mRectF.bottom
        }
        curPos.apply {
            left = mRectF.left
            top = mRectF.top
            right = mRectF.right
            bottom = mRectF.bottom
        }

        setBackgroundColor(Color.BLUE)
        setMeasuredDimension(curPos.width, curPos.height)
        mCurrentParams = FrameLayout.LayoutParams(curPos.width, curPos.height).apply {
                topMargin = curPos.topMargin
                leftMargin = curPos.leftMargin
            }
        layoutParams = mCurrentParams
        invalidate()
    }
//
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.restore()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        canvas.drawOval(curPos.left, curPos.top, curPos.right, curPos.bottom, paint)
    }
}

    // region position and translation
    fun onChangeSize(mRectF: RectF) {
        oldPos.apply {
            right = mRectF.right
            bottom = mRectF.bottom
        }
        curPos.apply {
            right = mRectF.right
            bottom = mRectF.bottom
        }
        mCurrentParams?.apply {
            leftMargin = curPos.leftMargin
            topMargin = curPos.topMargin
            width = curPos.width
            height = curPos.height
        }
        layoutParams = mCurrentParams
        invalidate()
    }

    fun onBeforeTransition() {
        oldPos.apply {
            top = curPos.top
            bottom = curPos.bottom
            left = curPos.left
            right = curPos.right
        }
        invalidate()
    }

    fun onTransition(mRectF: RectF) {
        Log.e("Canvas debug", "trans old position ${oldPos.print()}")
        curPos.apply {
            top = mRectF.bottom + oldPos.top - mRectF.top
            left = mRectF.right + oldPos.left - mRectF.left
            right = mRectF.right + oldPos.right - mRectF.left
            bottom = mRectF.bottom + oldPos.bottom - mRectF.top
        }
        Log.e("Canvas debug", "trans translate ${mRectF}")
        Log.e("Canvas debug", "trans cur position ${curPos.print()}")
        mCurrentParams?.apply {
            leftMargin = curPos.leftMargin
            topMargin = curPos.topMargin
        }
        layoutParams = mCurrentParams
        invalidate()
    }
    //endregion

    fun isClicked(pointF: PointF): Boolean {
        return pointF.y.roundToInt() in curPos.topMargin..curPos.maxVertical && pointF.x.roundToInt() in curPos.leftMargin..curPos.maxHorizontal
    }

    fun drawState() {
        setBackgroundColor(Color.RED)
    }

    fun clearSelected() {
        setBackgroundColor(Color.BLUE)
    }
}