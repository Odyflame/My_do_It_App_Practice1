package com.myapp.mysampelrecyclerview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        var layoutMAnager : LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = layoutMAnager
        var adapter = PersonAdapter()

        adapter.addItem(Person("kim miin soo", "010-1000-1901"))
        adapter.addItem(Person("kim ha neul", "010-1230-1901"))
        adapter.addItem(Person("kim kill dong", "010-2342-1901"))

        recyclerView.adapter = adapter
    }

    //리사이클러뷰는 껍데기 역할을 한다.
}
