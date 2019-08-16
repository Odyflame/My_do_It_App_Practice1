package com.myapp.mychallenge17

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout

class Pannelview: LinearLayout {

   /* lateinit var textView: TextView
    lateinit var textView2: TextView
    lateinit var textView3: TextView*/
    constructor(context: Context) : super(context){
        init(context)
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        init(context)
    }

    fun init(context : Context){
        /*var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.pannal_item, this)*/
        var inflater = LayoutInflater.from(context)//내부적으로 = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)호출
        inflater.inflate(R.layout.pannal_item, this, true)

        /*textView = findViewById(R.id.textView)
        textView2 = findViewById(R.id.textView)
        textView3 = findViewById(R.id.textView)*/
    }
/*    fun ViewGroup.inflate(resId: Int, attach: Boolean = false): View
            = LayoutInflater.from(context).inflate(resid, this, true)*/
}