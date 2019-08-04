package com.myapp.myfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_view.*

class viewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootview = inflater.inflate(R.layout.fragment_view, container, false)
        var imageView : ImageView = rootview.findViewById(R.id.imageView)

        return rootview
    }

    fun setImage(resid :Int){
        imageView.setImageResource(resid)
    }
}
