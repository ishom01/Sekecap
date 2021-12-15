package id.ishom.sekecap_draw.path

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

class Position(
    var left: Float = 0f,
    var top: Float = 0f,
    var right: Float = 0f,
    var bottom: Float = 0f
) {
    val widthF: Float get() {
        return abs(right - left)
    }

    val width: Int get() {
        return widthF.roundToInt()
    }

    val heightF: Float get() {
        return abs(bottom - top)
    }

    val height: Int get() {
        return heightF.roundToInt()
    }

    val leftMargin: Int get() {
        return min(left, right).roundToInt()
    }

    val maxHorizontal: Int get() {
        return max(left, right).roundToInt()
    }

    val maxVertical: Int get() {
        return max(top, bottom).roundToInt()
    }

    val topMargin: Int get() {
        return min(top, bottom).roundToInt()
    }

    fun print(): String {
        return "$left, $top, $right, $bottom"
    }
}