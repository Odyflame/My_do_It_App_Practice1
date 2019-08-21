package com.myapp.myhttp

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            var urlStr = editText.text.toString()
            Thread(object : Runnable{
                override fun run() {
                    request(urlStr)
                }
            }).start()
        }
    }

    fun request(uriStr : String){
        var output =  StringBuilder()
        try{
            var url = URL(uriStr)

            var conn = url.openConnection() as HttpURLConnection
            conn?. let{
                conn.connectTimeout=10000
                conn.requestMethod = "GET"
                conn.doInput = true

                var resCode = conn.responseCode
                var reader = BufferedReader(InputStreamReader(conn.inputStream))
                var line :String? = null

                while(true){
                    line = reader.readLine()
                    if(line==null) break

                    output.append(line + "\n")
                }
                reader.close()
                conn.disconnect()
            }
        }catch(ex : Exception){
            println("excepttion caused : " + ex.toString())
        }
        println("responsed : " + output.toString())
    }

    fun println(data :String){
        handler.post(object :Runnable{
            override fun run() {
                textView.append(data + "\n")
            }
        })
    }
}
