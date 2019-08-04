package com.myapp.myfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

open class ListFragment : Fragment() {

    interface ImageSelectionCallback{
        open fun onImageSelected(position:Int)
    }

    var callback : ImageSelectionCallback? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if(context is ImageSelectionCallback){
            callback = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_list, null)

        var button1:Button = view.findViewById(R.id.button)
        var button2:Button = view.findViewById(R.id.button2)
        var button3:Button = view.findViewById(R.id.button3)


        button1.setOnClickListener {
            callback?.let { callback!!.onImageSelected(0)}
        }

        button2.setOnClickListener {
            callback?.let {callback!!.onImageSelected(1)}
        }

        button3.setOnClickListener {
            callback?.let {callback!!.onImageSelected(2)}
        }

        return view
    }


}
