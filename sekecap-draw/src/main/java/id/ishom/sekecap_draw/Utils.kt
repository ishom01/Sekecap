package id.ishom.sekecap_draw

import android.graphics.RectF
import android.view.View
import id.ishom.sekecap_draw.widget.ShapeView
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

val RectF.width: Int get() {
    return abs(right - left).toInt()
}

val RectF.widthF: Float get() {
    return abs(right - left)
}

val RectF.height: Int get() {
    return abs(bottom - top).toInt()
}

val RectF.heightF: Float get() {
    return abs(bottom - top)
}

val RectF.minHorizontal: Int get() {
    return min(left, right).toInt()
}

val RectF.minVertical: Int get() {
    return min(top, bottom).toInt()
}
