package id.ishom.sekecap_draw.testing_widget

import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point
import id.ishom.sekecap_draw.path.DrawMode

class DrawPath: Path() {
    var drawMode = DrawMode.PEN
    var paint = Paint()
    var startPoint = Point(0, 0)
    var endPoint = Point(0, 0)

    fun draw() {

    }

    // for drawing frame when active
    fun drawFramePath() {

    }
}