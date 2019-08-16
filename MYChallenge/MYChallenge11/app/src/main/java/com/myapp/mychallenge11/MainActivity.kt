package com.myapp.mychallenge11

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var receiver:MyReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            var input:String = editText.text.toString()

            sendToService(input)
        }

        //이전 버전은 리시버를 사용해서 서비스로부터 받았다.
        /*receiver = MyReceiver()

        var filter :IntentFilter = IntentFilter()
        filter.addAction("com.myapp.mychallenge11.SHOW")
        registerReceiver(receiver, filter)*/

        var intent = intent
        processIntent(intent)

    }

    inner class MyReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            processIntent(p1!!)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        processIntent(intent!!)
    }

    fun processIntent(intent :Intent){
        intent?.let {
            var command = intent.getStringExtra("command")

            //이전 버전 command와 data를 서로 다르게 확인
           /* if(command!=null && command == "show")
            {
                var data = intent.getStringExtra("data")
                textView.setText("받은 결과 : "+ data)
            }*/

            var data = intent.getStringExtra("data")

            data?. let{ textView.setText("받은 결과 : "+ data) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(receiver)
    }

    fun sendToService(str :String){
        var serviceIntent:Intent = Intent(applicationContext, MyService::class.java)
        serviceIntent.putExtra("command", "show")
        serviceIntent.putExtra("data", str)

        startService(serviceIntent)
    }
}
