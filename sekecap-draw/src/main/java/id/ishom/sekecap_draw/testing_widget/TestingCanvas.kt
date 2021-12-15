package id.ishom.sekecap_draw.testing_widget

import android.content.Context
import android.graphics.*
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import id.ishom.sekecap_draw.height
import id.ishom.sekecap_draw.width

class TestingCanvas @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr), View.OnClickListener {

    private var paint = Paint().apply {
        color = Color.GREEN
        strokeWidth = 2f
        style = Paint.Style.FILL
    }

    init {
        setOnClickListener(this)
    }

    private var paintHint = Paint().apply {
        color = Color.RED
        strokeWidth = 2f
        strokeCap = Paint.Cap.ROUND
        style = Paint.Style.STROKE
    }

//    override fun dispatchDraw(canvas: Canvas) {
//        super.dispatchDraw(canvas)
//        val path = Path()
//        val rect = RectF(10f, 10f, 100f, 100f)
//        path.addRect(rect, Path.Direction.CW)
//
//        val framePath = Path()
//        framePath.addRect(rect, Path.Direction.CCW)
//        framePath.moveTo(0f, 0f)
//        framePath.lineTo(0f, 20f)
//        framePath.lineTo(20f, 20f)
//        framePath.lineTo(20f, 0f)
//        framePath.lineTo(0f, 0f)
//
//        framePath.moveTo(90f, 90f)
//        framePath.lineTo(90f, 110f)
//        framePath.lineTo(110f, 110f)
//        framePath.lineTo(110f, 90f)
//        framePath.lineTo(90f, 90f)
//
//        canvas.drawPath(path, paint)
//        canvas.drawPath(framePath, paintHint)
//
//        Log.e("Draw","Calling again??")
//        setMeasuredDimension(110, 110)
//        requestLayout()
//    }

    fun drawRectangle(mRect: RectF) {
        val canvas = Canvas()
//        canvas.drawRect(RectF(0f, 0f, mRect.widthF, mRect.heightF), paint)
        draw(canvas)
        invalidate()
        setMeasuredDimension(mRect.width, mRect.height)
    }

    override fun onClick(view: View?) {
        setBackgroundColor(Color.RED)
    }
}