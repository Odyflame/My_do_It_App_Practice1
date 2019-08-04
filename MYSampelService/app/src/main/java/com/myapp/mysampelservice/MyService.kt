package com.myapp.mysampelservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

 class MyService : Service() {
     override fun onBind(p0: Intent?): IBinder? {
         return null
     }

     val TAG :String = "MyService"

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d(TAG, "onStartCommand called")

        if(intent==null) return Service.START_STICKY
        else processCommand(intent)

        return super.onStartCommand(intent, flags, startId)
    }

    fun processCommand(intent: Intent){

        var command :String = intent.getStringExtra("command")
        var name :String = intent.getStringExtra("name")

        Log.d(TAG, "processCommand called")

        for(i in 0..4){
            try{
                Thread.sleep(1000)
            }catch (e : Exception){}

            Log.d(TAG, "waiting" + i + "second.")
        }

        var showIntent:Intent = Intent(applicationContext, MainActivity::class.java)

        //showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_SINGLE_TOP, Intent.FLAG_ACTIVITY_CLEAR_TASK)

        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        showIntent.putExtra("command", "show")
        showIntent.putExtra("name", name + "from service")
        startActivity(showIntent)

    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }

    //수명주기를 관리하기 위해 onStart와 onDestroy도 관리할 수 있다.
}
