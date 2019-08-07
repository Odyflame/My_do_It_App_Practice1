package com.myapp.mysampellayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layout1.setImage(R.drawable.profile1)
        layout1.setName("kim min soo")
        layout1.setMobile("010-3455-2324")

        button.setOnClickListener {
            layout1.setImage(R.drawable.profile1)
        }

        button2.setOnClickListener {
            layout1.setImage(R.drawable.profile2)
        }
    }
}
