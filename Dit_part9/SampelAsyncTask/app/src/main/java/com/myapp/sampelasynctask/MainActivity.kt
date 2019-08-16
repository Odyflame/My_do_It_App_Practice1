package com.myapp.sampelasynctask

import android.os.AsyncTask
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var progressVar :ProgressBar
    lateinit var task :BackgroundTask
    var value : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressVar = findViewById(R.id.PBar)

        gobu.setOnClickListener {
            task = BackgroundTask()
            task.execute()
        }

        cancelbu.setOnClickListener {
            task.cancel(true)
        }

    }

    inner class BackgroundTask : AsyncTask<Int, Int, Int>() {

        protected override fun onPreExecute(){
            value = 0
            progressVar.setProgress(value)
        }

        override fun doInBackground(vararg p0: Int?): Int {
            while(!isCancelled){
                value++
                if(value >= 100){
                    break
                }else publishProgress(value)

                try {
                    Thread.sleep(1000)
                }catch (ex : InterruptedException){}
            }

            return value
        }

        override fun onProgressUpdate(vararg values: Int?) {
            progressVar.setProgress(values[0]?.toInt()!!)
        }

        override fun onPostExecute(result: Int?) {
            progressVar.setProgress(0)
        }

        override fun onCancelled() {
            progressVar.setProgress(0)
        }
    }
}
