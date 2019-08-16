package com.myapp.mysampellooper

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var thread : ProcessThread
    var handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            var input = editText.text.toString()
            var msg = Message.obtain()
            msg.obj  =input

            thread.processHandler.sendMessage(msg)
        }
    }

    inner class ProcessThread : Thread(){

        var processHandler = ProcessHandler()

        override fun run() {
            Looper.prepare()
            Looper.loop()
        }
    }

    inner class ProcessHandler : Handler(){
        fun handleMessage1(msg:Message){
            var output:String = "${msg.obj.toString()}" + " From Thread"

            handler.post(object : Runnable {p
                override fun run() {
                    textview1.text = output
                }
            })
        }
    }
}