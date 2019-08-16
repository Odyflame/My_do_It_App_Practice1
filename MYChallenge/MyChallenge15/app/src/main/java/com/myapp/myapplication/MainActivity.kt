package com.myapp.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isPageOpen = false

    //lateinit var page : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gotoInput.setOnClickListener {

            /*var transright = AnimationUtils.loadAnimation(this, R.anim.translate_right)
            var transrleft = AnimationUtils.loadAnimation(this, R.anim.translate_left)

            var animlistner =SlidingPageListner()

            transright.setAnimationListener(animlistner)
            transrleft.setAnimationListener(animlistner)

            if(isPageOpen){
                page.visibility = View.INVISIBLE
                page.startAnimation(transright)
            }else{
                page.visibility = View.VISIBLE
                page.startAnimation(transrleft)
            }*/

            var myintent = Intent(this, ContentActivity::class.java)


           startActivity(myintent)


            //overridePendingTransition(R.anim.entry, R.anim.exit)
        }
    }



    inner private class SlidingPageListner : Animation.AnimationListener{
        override fun onAnimationRepeat(p0: Animation?) {

        }

        override fun onAnimationEnd(p0: Animation?) {

        }

        override fun onAnimationStart(p0: Animation?) {
            if(isPageOpen){
                //page.visibility = View.INVISIBL

                isPageOpen=false
            }else{

                isPageOpen= true
            }
        }

    }
}
