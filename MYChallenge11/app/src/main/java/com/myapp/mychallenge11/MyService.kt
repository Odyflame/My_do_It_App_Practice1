package com.myapp.mychallenge11

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {

    //최신 버전인것같다.
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?. let {
            var command :String = intent.getStringExtra("command")
            if(command!=null && command == "show")
            {6
                var data = intent.getStringExtra("data")

                sendToActivity(data)
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    //첫번째 버전은 서비스에서 입력할 글자로 가는 경우
    /*fun sendToActivity(data: String) {
        val activityIntent = Intent()
        activityIntent.action = "com.myapp.mychallenge11.SHOW"
        activityIntent.putExtra("command", "show")
        activityIntent.putExtra("data", data)

        sendBroadcast(activityIntent)
    }*/

    fun sendToActivity(data: String) {

        var activityIntent = Intent(applicationContext, MainActivity::class.java)
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activityIntent.putExtra("command", "show")
        activityIntent.putExtra("data", data)

        startActivity(activityIntent)
    }

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
