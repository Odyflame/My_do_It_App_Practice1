package com.myapp.mysampelpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.myapp.mysampletab.fragment1
import com.myapp.mysampletab.fragment2
import com.myapp.mysampletab.fragment3
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var pager1 :ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            pager1.setCurrentItem(1)
        }

        pager1 = findViewById(R.id.pager)
        pager1.offscreenPageLimit = 3

        val adapter = MyAdapter(supportFragmentManager)

        val frag1 = fragment1()
        adapter.additem(frag1)

        val frag2 = fragment2()
        adapter.additem(frag2)

        val frag3 = fragment3()
        adapter.additem(frag3)

        pager1.adapter = adapter
    }

    class MyAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

        var items = ArrayList<androidx.fragment.app.Fragment>()

        fun additem(myfm : Fragment){
            items.add(myfm)
        }

        override fun getItem(position: Int): androidx.fragment.app.Fragment? {
           return items.get(position)
        }

        override fun getCount(): Int {
            return items.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return "page : " + position
        }

    }
}
