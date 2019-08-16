package com.myapp.mysampeltweenanimation

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isPageOpen = false

   lateinit var page :LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sliding)

        button.setOnClickListener {
    /*        var anim =AnimationUtils.loadAnimation(this, R.anim.scale)

            it.startAnimation(anim)*/

            page = findViewById(R.id.page)

            var translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left)
            var translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right)

            var animlistner = SlidingPageListner()
            translateLeftAnim.setAnimationListener(animlistner)
            translateRightAnim.setAnimationListener(animlistner)

            if(isPageOpen){
                page.visibility = View.INVISIBLE
                page.startAnimation(translateRightAnim)
            }else{
                page.visibility = View.VISIBLE
                page.startAnimation(translateLeftAnim)
            }
        }
    }

    inner private class SlidingPageListner :Animation.AnimationListener{
        override fun onAnimationRepeat(p0: Animation?) {

        }

        override fun onAnimationEnd(p0: Animation?) {

        }

        override fun onAnimationStart(p0: Animation?) {
            if(isPageOpen){
                page.visibility = View.INVISIBLE

                button.setText("Open")
                isPageOpen=false
            }else{

                button.text = "Close"
                isPageOpen= true
            }
        }

    }
}
