package com.myapp.mychallenge

import android.content.Context
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout

class PictureItemView :LinearLayout{
    constructor(context : Context) :super(context){ init(context) }
    constructor(context : Context, attrs : AttributeSet) :super(context, attrs){ init(context) }

    var options = BitmapFactory.Options()

    fun init(context : Context){
        var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.picture_item, this, true)

        options.inSampleSize = 12
    }

   /* fun setImage(path : String){
        var bitmap = BitmapFactory.decodeFile(paht, option
    }*/
}