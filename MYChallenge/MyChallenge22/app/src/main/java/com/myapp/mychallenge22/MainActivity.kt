package com.myapp.mychallenge22

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity(), OnDatabaseCallback {
    override fun selectAll(): ArrayList<Bookinfo> {
        var result= database!!.selectAll()
        Toast.makeText(applicationContext, "ask bookinfo", Toast.LENGTH_SHORT).show()

        return result
    }

    val TAG = "MainActivity"
    var fragment1 = Fragment1()
    var fragment2 = Fragment2()

    var database : BookDatabase? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*var toolbar :Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)*/

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment1).commit()

        var tabs :TabLayout = findViewById(R.id.tabs)
        tabs.addTab(tabs.newTab().setText("input"))
        tabs.addTab(tabs.newTab().setText("inquery"))

        tabs.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}

            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                var position = tab!!.position
                Log.d("MainActivity", "selected tab : " + position)

                var selected :Fragment? = null
                when(position){
                    0 -> selected = fragment1
                    1-> selected = fragment2
                }

                supportFragmentManager.beginTransaction().replace(R.id.container, selected!!).commit()
            }
        })

        database?.let { database!!.close(); database = null}

        database = BookDatabase.getInstance(this)
        var isOpen = database!!.open()

        if(isOpen) Log.d(TAG, "Book database is open")
        else Log.d(TAG, "Book database is close")

    }

    override fun onDestroy() {
        // close database
        if (database != null) {
            database!!.close()
            database = null
        }

        super.onDestroy()
    }

    override fun insert(name :String, author :String, contents : String){
        database!!.insertRecord(name, author, contents)
        Toast.makeText(applicationContext, "added book info", Toast.LENGTH_LONG).show()
    }
}
