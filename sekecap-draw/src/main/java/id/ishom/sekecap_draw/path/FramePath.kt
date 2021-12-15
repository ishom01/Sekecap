package id.ishom.sekecap_draw.path

import android.content.Context
import android.graphics.Path


class FramePath: Path() {

    lateinit var context: Context
    lateinit var basePath: IPath

    var pathFrameMiniBoxSize = 20f

}