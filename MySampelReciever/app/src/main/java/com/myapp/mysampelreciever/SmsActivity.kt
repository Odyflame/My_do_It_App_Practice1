package com.myapp.mysampelreciever

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sms.*

class SmsActivity : AppCompatActivity() {

    lateinit var editText1 :EditText
    lateinit var editText2 :EditText
    lateinit var editText3 :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)

        editText1 = findViewById((R.id.editText))
        editText2 = findViewById((R.id.editText2))
        editText3 = findViewById((R.id.editText3))

        button.setOnClickListener {
            finish()
        }

        var passedIntent = intent
        processIntent(passedIntent)
    }

    override fun onNewIntent(intent: Intent?) {
        processIntent(intent!!)
        super.onNewIntent(intent)
    }

    fun processIntent(intent: Intent){
        intent?.let {
            var sender:String = intent.getStringExtra("sender")
            var contents = intent.getStringExtra("contents")
            var receivedDate = intent.getStringExtra("receivedDate")

            editText1.setText(sender)
            editText1.setText(contents)
            editText1.setText(receivedDate)
        }
    }
}
