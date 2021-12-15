package id.ishom.sekecap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_other_testing_canvas.*

class OtherTestingCanvas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_testing_canvas)

        testingView.top = 200
        testingView.left = 100
    }
}