package com.myapp.sampleoptionproject

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var abar:ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        abar = this!!.supportActionBar!!

        var button : Button = findViewById(R.id.button)
        button.setOnClickListener {
            abar!!.setLogo(R.drawable.home)
            Toast.makeText(this, "Sdfsdf", Toast.LENGTH_SHORT).show()
            abar!!.setDisplayOptions(ActionBar.DISPLAY_USE_LOGO)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mennu_main, menu)//꼭해야 앱이 실행됨

        var v : View = menu!!.findItem(R.id.menu_search).actionView//메뉴에서 검색메뉴를 찾아 v에 저장
        v?.let {
            //v가 널이아닐경우
            var edittext : EditText = v.findViewById(R.id.editText)

            edittext?. let { editText -> editText.setOnEditorActionListener { textView, i, keyEvent -> Toast.makeText(applicationContext, "입력됨", Toast.LENGTH_SHORT).show(); true} }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var curid :Int = item.itemId
        when(curid){
            R.id.menu_refresh -> Toast.makeText(this, "새로고침 메뉴 선택", Toast.LENGTH_SHORT).show()
            R.id.menu_search -> Toast.makeText(this, "검색 메뉴가 선택되었습니다", Toast.LENGTH_SHORT).show()
            R.id.menu_settings ->Toast.makeText(this, "설정 메뉴가 선택되었습니다", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }


}
