package com.myapp.mychallenge6

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mySeeksvar = seekBar2

        seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                var prograssar: ProgressBar = findViewById(R.id.progressBar)
                editText.setText("$p1")
                prograssar.setIndeterminate(false)
                prograssar.setProgress(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                toast("start tracking")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                toast("end tracking")
            }
        })
    }

    fun onButtonClick(veiw : View){
        var textNumber:Int? = editText.text.toString().toInt()
        var prograssar: ProgressBar = findViewById(R.id.progressBar)
        when(textNumber) {
            null -> toast("please insert number under 100")
            else -> {
                prograssar.setIndeterminate(false)
                prograssar.setProgress(textNumber)
            }
        }

    }
}
