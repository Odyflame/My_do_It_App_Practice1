package com.myapp.mychallenge22_again

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    var TAG = "MainActivity"
    var fragment1 = Fragment1()
    var fragment2 = Fragment2()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction().replace(R.id.container, fragment1).commit()

        var tabs : TabLayout = findViewById(R.id.tabs)
        tabs.addTab(tabs.newTab().setText("input"))
        tabs.addTab(tabs.newTab().setText("inquery"))

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) { }
            override fun onTabSelected(p0: TabLayout.Tab?) {
                var position = p0!!.position
                Log.d("MainActivity", "selected tab : " + position)

                var selected: Fragment? = null

            }
        })

    }
}
