package com.myapp.mychallenge8

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menu.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        Clientbu.setOnClickListener {
            toast("fsdf")
            var intent = Intent()
            intent.putExtra("name", "mike")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        button2.setOnClickListener {
            startActivity<boxActivity>()
        }
    }

    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(parent, name, context, attrs)
    }

}
