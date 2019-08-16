package com.myapp.mysampelthread

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var value = 0
    lateinit var textview : TextView
    var handeler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textview = findViewById(R.id.textView)

        button.setOnClickListener {
            var mythread = BackgroundThread()
            mythread.start()
        }

       // handeler = BackgroundThread.Handeler()
    }

    inner class BackgroundThread : Thread() {
        override fun run() {
            for (i in 1..100) {
                try {
                    Thread.sleep(1000)
                } catch (e: Exception) {
                }

                value++
                Log.d("Thread", "value : " + value)

                //일반적인 방법의 핸들러
                /* var msg :Message = handeler.obtainMessage()
                var bundle = Bundle()
                bundle.putInt("value", value)
                msg.data = bundle

                handeler.sendMessage(msg)*/

                val runnable = Runnable { textview.text = "value : " + value}

                handeler.post(runnable)

                /*//러너블 객체 실행
                override fun run() {
                    textview.text = "value : " + value
                }
                handeler.post(object : Runnable {
                })*/


            }
        }

       /* inner class Handeler : Handler() {

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)

                var bundle = msg.data
                var value: Int = bundle.getInt("value")
                textview.text = "value : " + value

            }
        }*/
    }
}
