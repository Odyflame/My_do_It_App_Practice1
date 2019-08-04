package com.myapp.mysampelservice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var editText:EditText = findViewById(R.id.editText)

        button.setOnClickListener {
            var name :String = editText.text.toString()

            var intent :Intent = Intent(applicationContext, MyService::class.java)

            intent.putExtra("command", "name")
            intent.putExtra("name", name)
            startService(intent)


        }
        var passIntent :Intent? = intent//되지 않는데 그 이유를 모르겠다.
        processIntent(passIntent)
    }

    override fun onNewIntent(intent: Intent?) {

        processIntent(intent)

        super.onNewIntent(intent)
    }

     fun processIntent(intnet:Intent?){

        if(intent!=null){
            var command :String = intent.getStringExtra("command")
            var name:String = intent.getStringExtra("name")

            longToast("command : " + command + ", name : " + name)
        }

    }
}
