package com.myapp.mychallenge19

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var task :BackgroundTask
    lateinit var webrview : WebView
    lateinit var url :String
    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webrview = findViewById(R.id.webView)
        webrview.settings.javaScriptEnabled=true

        button.setOnClickListener {
            url = editText.text.toString()

        }
    }

    inner class RequestThread : Thread(){
        override fun run(){
            try{
                handler.post(object : Runnable{
                    override fun run() {
                        textView.text = 
                    }
                })
            }catch(ex :Exception){
                ex.printStackTrace()
            }
        }
    }

}