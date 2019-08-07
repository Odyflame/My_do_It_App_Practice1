package com.myapp.mysampellayout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.layout1.view.*

class layout1 : LinearLayout {

    /*lateinit var imageView: ImageView
    lateinit var textview : TextView
    lateinit var textview2 : TextView*/

    constructor(context: Context) :super(context){
        init(context)
    }
    constructor(context: Context, attrs : AttributeSet) :super(context, attrs){
        init(context)
    }

    fun init(context: Context){

        //버튼이 화면에 나타나지 않고 그냥 메모리에 올리는 과정을 테스트한다.
        //xml에 명시한 위젯이 안드로이드의 실제 위젯으로 메모리에 형성되는 과정을 인플레이션이라고 한다.

        //안드로이드에서는 xml에 명시한 위젯을 메모리에 올리는 과정을 개발자가 직접 수행할 수 있도록 inflator를 지원한다.
        var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout1, this, true)

    }

    fun setImage(resID : Int){
        imageView.setImageResource(resID)
    }

    fun setName(resID : String){
        textView.setText(resID)
    }

    fun setMobile(resID : String){
        textView2.setText(resID)
    }
}