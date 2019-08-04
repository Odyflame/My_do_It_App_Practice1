package com.myapp.mysampletab

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var toolvar: Toolbar? = findViewById(R.id.toolbar)

        setSupportActionBar(toolvar)

        var actionvar : ActionBar = this!!.supportActionBar!!

        var myfragment1:fragment1 = fragment1()
        var myfragment2:fragment2 = fragment2()
        var myfragment3:fragment3 = fragment3()

       supportFragmentManager.beginTransaction().replace(R.id.container, myfragment1).commit()

        var tabs :TabLayout = findViewById(R.id.tabs)
        tabs.addTab(tabs.newTab().setText("통화기록"))
        tabs.addTab(tabs.newTab().setText("스팸기록"))
        tabs.addTab(tabs.newTab().setText("연락처"))

        tabs.addOnTabSelectedListener( object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                var position :Int = p0!!.position
                var selected:Fragment? = null
                when{
                    position == 0 -> selected = myfragment1
                    position == 1 -> selected = myfragment2
                    position == 2 -> selected = myfragment3
                }

                supportFragmentManager.beginTransaction().replace(R.id.container, selected!!).commit()
            }

        } )
    }
}
