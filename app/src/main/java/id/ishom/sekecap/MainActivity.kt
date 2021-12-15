package id.ishom.sekecap

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_remove.setOnClickListener {
            layout.mSelectedView = null
            layout.removeAllViews()
        }

        btn_convert.setOnClickListener {
            Log.e("Debug", "draw actiity width ${layout.width}")
            Log.e("Debug", "draw actiity width ${layout.height}")

            Log.e("Debug", "draw actiity width ${layout.measuredWidth}")
            Log.e("Debug", "draw actiity width ${layout.measuredHeight}")

            image.setImageBitmap(layout.getBitmap())
        }
    }
}