package com.myapp.mysampelview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton

class MyButton : AppCompatButton{
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        Log.d("My Button", "onTouchEvent 호출됨")

        var action = event!!.action
        when(action){
            MotionEvent.ACTION_DOWN->{
                setBackgroundColor(Color.BLUE)
                setTextColor(Color.RED)
            }
            MotionEvent.ACTION_OUTSIDE, MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP ->{
                setBackgroundColor(Color.CYAN)
                setTextColor(Color.BLACK)
            }
        }

        invalidate()

        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        Log.d("My Button", "onDraw 호출됨")
    }

    init{
        context
    }

    constructor(context : Context) : super(context) {
        initcontext(context)
    }

    constructor(context: Context, attrs : AttributeSet) : super(context, attrs){
        initcontext(context)
    }

    private fun initcontext(context: Context){
        setBackgroundColor(Color.CYAN)
        setTextColor(Color.BLACK)

        var textSize :Float = resources.getDimension(R.dimen.text_size)
        setTextSize(textSize)
    }

}