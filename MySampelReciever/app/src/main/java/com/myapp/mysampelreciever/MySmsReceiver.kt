package com.myapp.mysampelreciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class MySmsReceiver : BroadcastReceiver() {

    var format :SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    var TAG :String = "MySmsReciver"

    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, "onReceive 메서드 호출됨")

        var bundle = intent.extras

        var messages:Array<SmsMessage> = parseMessage(bundle)

        messages?.let {
            if (messages.size > 0) {
                var sender: String? = messages[0].originatingAddress
                Log.i(TAG, "SMS sender : " + sender)

                var contents: String? = messages[0].messageBody.toString()
                Log.i(TAG, "SMS contents : " + contents)

                var receivedDate: Date = Date(messages[0].timestampMillis)
                Log.i(TAG, "SMS receivedDate : " + receivedDate)

                sendToActivity(context, sender!!, contents!!, receivedDate)
            }
        }

    }

    fun sendToActivity(context: Context, sender :String, contents:String, receivedDate : Date){
        var myintent = Intent(context, SmsActivity::class.java)

        myintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        myintent.putExtra("sender", sender)
        myintent.putExtra("contents", contents)
        myintent.putExtra("receivedDate", format.format(receivedDate))

        context.startActivities(arrayOf(myintent))
    }

    //앱을 만들 때 재사용할 수 있다. sms 데이터를 확인할 수 있도록 안드로이드 API에 정해둔 코드를 사용하므로 수정될 일이 거의 없기 때문이다
    //인텐트 객체 안에 부가 데이터로 들어있는 sms 데이터를 확이하려면 smsmenager클래스의 createfromPdu메서드를 사용하여 smsMessage객체로 변환하면 sms데이터를 확인 가능
    fun parseMessage(bundle: Bundle?) : Array<SmsMessage>{

        var objs = bundle!!.get("pdus") as Array<Object>

        var message  = arrayOf(objs.size ) as Array<SmsMessage>

        var smsCount = objs.size
        for(i in 1..smsCount){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                var format : String? = bundle.getString("format")
                message[i] = SmsMessage.createFromPdu(objs[i] as ByteArray, format!!)
            }else{
                message[i] = SmsMessage.createFromPdu(objs[i] as ByteArray)
            }
        }

        return message
    }
}
