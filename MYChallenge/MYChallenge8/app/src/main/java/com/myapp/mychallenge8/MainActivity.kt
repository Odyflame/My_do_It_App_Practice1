package com.myapp.mychallenge8

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

data class simpledata(var x:Int, var y : String)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun onClick(view : View){
        var idview = IDText.text.toString()
        var pwview = PAswordText.text.toString()

        if(idview==null || pwview==null) {
            toast("정확히 입력해주세요")
        }else{
            var data =simpledata(3, "helloworld")
            var intent = Intent(this, MenuActivity::class.java)
            startActivityForResult(intent, 101)
        }

    }
}
