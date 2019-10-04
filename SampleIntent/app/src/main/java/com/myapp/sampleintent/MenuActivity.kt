package com.myapp.sampleintent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        button2.setOnClickListener {
            var intent = Intent()
            intent.putExtra("name", "mike")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        

    }

}

