package com.myapp.mychallenge25

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity()  {

    var dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
    var pictureCount=0

    lateinit var pictures : ArrayList<Pictureinfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
