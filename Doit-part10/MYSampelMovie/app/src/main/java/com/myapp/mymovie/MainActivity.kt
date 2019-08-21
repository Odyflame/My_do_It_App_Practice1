package com.myapp.mymovie

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter : MovieAdapter
    lateinit var recyclerview : RecyclerView

    var requestQueue :RequestQueue?=null

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            makeRequest()
        }

        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(applicationContext)
        }

        recyclerview = findViewById(R.id.recyclerview)

        var layoutmanager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter = MovieAdapter()
        recyclerview.layoutManager = layoutmanager
        recyclerview.adapter = adapter

    }

    fun makeRequest(){
        var url = editText.text.toString()

        var request = object : StringRequest(Request.Method.GET, url, Response.Listener<String>{
            response: String -> println("Response -> " + response)
            processResponse(response)
        }, Response.ErrorListener{
            error: VolleyError? ->  println("Error -> " + error!!.message)
            Toast.makeText(applicationContext, "Error: " + (error.toString()), Toast.LENGTH_SHORT).show()
        }){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                return params
            }
        }

        request.setShouldCache(false)
        requestQueue!!.add(request)
        println("send Request")
    }

    fun processResponse(response : String){
        var gson = Gson()
        var movielist = gson.fromJson(response, movieList::class.java)

        println("movie numbers : " + movielist.boxOfficeResult!!.dailyBoxOfficeList.size)

        for( i in 0..movielist.boxOfficeResult!!.dailyBoxOfficeList.size-1){
            var movie = movielist.boxOfficeResult!!.dailyBoxOfficeList.get(i)

            adapter.additem(movie)
        }
        adapter.notifyDataSetChanged()
    }

    fun println(str :String){
        Log.d("MainActivity", str + "\n")
    }
}
