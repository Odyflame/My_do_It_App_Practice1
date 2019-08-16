package com.myapp.mychallenge17

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.pannal_item.view.*

class MainActivity : AppCompatActivity() {

    lateinit var task :BackgroundTask
    var isRunning = false
    var selected = 1

    lateinit var pannel1 :Pannelview
    lateinit var pannel2 :Pannelview
    lateinit var translateLeft : Animation
    lateinit var translateRight : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pannel1 = Pannelview(this)
        pannel2 = Pannelview(this)

        pannel1.textView.text = "hong gil dong"
        pannel2.textView2.text = "010-1234-5432"
        pannel2.textView3.text = "seoul"
        container.addView(pannel1)

        pannel2.textView.text = "kim min soo"
        pannel2.textView2.text = "010-9876-5432"
        pannel2.textView3.text = "busan"
        container.addView(pannel2)

        translateLeft = AnimationUtils.loadAnimation(this, R.anim.translate_left)
        translateRight = AnimationUtils.loadAnimation(this, R.anim.translate_right)

        button.setOnClickListener {
            isRunning = true
            task = BackgroundTask()
            task.execute()
        }

        button2.setOnClickListener {
            task.cancel(true)
            isRunning = false
        }
    }

   /* inner class SlidintPagingAnimationListner :Animation.AnimationListener {
        override fun onAnimationRepeat(p0: Animation?) {

        }

        override fun onAnimationEnd(p0: Animation?) {
            if(isRunning){


            }
        }

        override fun onAnimationStart(p0: Animation?) {

        }
    }*/

    inner class BackgroundTask : AsyncTask<Int, Int, Int>(){

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onCancelled() {
            super.onCancelled()
            isRunning = false
        }

        @SuppressLint("WrongThread")
        override fun doInBackground(vararg p0: Int?): Int {
            while(isRunning){

                if(selected==1){
                    pannel1.startAnimation(translateLeft)
                    pannel2.startAnimation(translateRight)
                }else{
                    pannel1.startAnimation(translateRight)
                    pannel2.startAnimation(translateLeft)
                }
                selected++
                if(selected > 1) selected=0

                try{
                    Thread.sleep(2000)
                }catch(ex : Exception){}

            }
            return 1
        }

        override fun onPostExecute(result: Int?) {
            super.onPostExecute(result)
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)


        }
    }
}
