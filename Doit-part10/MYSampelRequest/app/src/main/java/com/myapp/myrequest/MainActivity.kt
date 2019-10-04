package com.myapp.myrequest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

     var requestQueue : RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            makeRequest()
        }

        if(requestQueue==null) {
            requestQueue = Volley.newRequestQueue(applicationContext)
        }
    }

    fun makeRequest(){
        var url = editText.text.toString()

        fun processResponse(response : String){
            var gson = Gson()
            var movielist = gson.fromJson(response, movieList::class.java)
            println("the number of Movies : " + movielist.boxOfficeResult!!.dailyBoxOfficeLsit.size)
        }

        var request = object : StringRequest(Request.Method.GET, url,Response.Listener<String>{
            response :String -> println("response : " + response)
            processResponse(response)
        }, Response.ErrorListener {
            error: VolleyError? -> println("error : " + error)
            Toast.makeText(applicationContext, "Error: " + (error.toString()), Toast.LENGTH_SHORT).show()
        }){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>() as MutableMap<String, String>
                return params
            }
        }

        request.setShouldCache(false)
        requestQueue!!.add(request)
        println("send request")
    }

   fun println(data:String){
       textView.append(data + "\n")
   }
}
