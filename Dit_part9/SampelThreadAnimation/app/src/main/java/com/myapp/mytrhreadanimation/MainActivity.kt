package com.myapp.mytrhreadanimation

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var handeler = Handler()
    var drawbleList = ArrayList<Drawable>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var res = resources
        drawbleList.add(res.getDrawable(R.drawable.face1))
        drawbleList.add(res.getDrawable(R.drawable.face2))
        drawbleList.add(res.getDrawable(R.drawable.face3))
        drawbleList.add(res.getDrawable(R.drawable.face4))
        drawbleList.add(res.getDrawable(R.drawable.face5))

        button.setOnClickListener {
            var thread = Animthread()
            thread.start()
        }
    }

    inner class Animthread : Thread(){
        override fun run() {
            var index = 0
            for(i in 1..100){
                index++
                if(index>4) index=0

                handeler.post(object : Runnable{
                    override fun run() {
                        imageView.setImageDrawable(drawbleList.get(index))
                    }
                })
                try{
                    Thread.sleep(1000)
                }catch (ex : Exception){
                    ex.printStackTrace()
                }
            }
        }
    }
}
