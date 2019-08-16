package com.myapp.mysocket

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.ServerSocket
import java.net.Socket

class MainActivity : AppCompatActivity() {

    var handeler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            var data :String = editText.text.toString()
            Thread(object : Runnable {
                override fun run() {
                    send(data)
                }
            }).start()
        }

        button2.setOnClickListener {
            Thread(object :Runnable {
                override fun run() {
                    startServer()
                }
            }).start()
        }
    }

    fun printClientLogg(data :String){
        Log.d("MainActivity", data)
        handeler.post(object:Runnable {
            override fun run() {
                textView.append(data + "\n")
            }
        })
    }

    fun printServerLog(data :String){
        Log.d("MainActivity", data)

        handeler.post(object:Runnable{
            override fun run() {
                textView2.append(data + "\n")
            }
        } )
    }

    fun send(data: String){
        try{
            var portNumber = 5001
            var sock : Socket = Socket("localhost", portNumber)
            printClientLogg("connected Socket")
            var outStream = ObjectOutputStream(sock.getOutputStream())
            outStream.writeObject(data)
            outStream.flush()
            printClientLogg("data sended")

            var inStream = ObjectInputStream(sock.getInputStream())
            printClientLogg("from server : " + inStream.readObject())
            sock.close()
        }catch(ex : Exception){
            ex.printStackTrace()
        }
    }

    fun startServer(){
        try{
            var portNumber = 5001

            var server = ServerSocket(portNumber)
            printServerLog("start Server : " + portNumber)
            while(true){
                var sock :Socket = server.accept()
                var clientHost = sock.localAddress
                var clientPort = sock.port
                printServerLog("connected Client : " + clientHost + " : " + clientPort)

                var inStream = ObjectInputStream(sock.getInputStream())
                var obj = inStream.readObject()
                printServerLog("get data from server : " + obj)

                var outStream = ObjectOutputStream(sock.getOutputStream())
                outStream.writeObject("from server : " + obj)
                outStream.flush()
                printServerLog("send data")

                sock.close()
            }
        }catch(ex :java.lang.Exception){
            ex.printStackTrace()
        }
    }
}
