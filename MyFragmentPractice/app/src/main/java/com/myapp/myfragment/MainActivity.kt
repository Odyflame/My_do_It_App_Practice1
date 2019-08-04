package com.myapp.myfragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity(), ListFragment.ImageSelectionCallback {

    lateinit var listFragment : ListFragment
    lateinit var viewfragment :viewFragment
    var image = arrayOf(R.drawable.dream01, R.drawable.dream02, R.drawable.dream03)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var manager : FragmentManager = supportFragmentManager
        listFragment = manager.findFragmentById(R.id.listFragment) as ListFragment
        viewfragment = manager.findFragmentById(R.id.viewFragemnt) as viewFragment

    }

    override fun onImageSelected(pa :Int) {
        viewfragment.setImage(image[pa])
    }

}
