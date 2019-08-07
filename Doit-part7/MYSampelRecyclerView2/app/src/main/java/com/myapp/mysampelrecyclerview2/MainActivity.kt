package com.myapp.mysampelrecyclerview2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView



class MainActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        var layoutMAnager = GridLayoutManager(this, 2)

        recyclerView.layoutManager = layoutMAnager

        var adapter = PersonAdapter()

        adapter.addItem(Person("kim min soo", "010-2342-2342"))
        adapter.addItem(Person("kim min ssu", "010-2342-4232"))
        adapter.addItem(Person("kim min ssuk", "010-2342-2yr2"))
        adapter.addItem(Person("ssuk1", "010-2342-2yr2"))
        adapter.addItem(Person("ssuk2", "010-2342-2yr2"))
        adapter.addItem(Person("ssuk3", "010-2342-2yr2"))
        adapter.addItem(Person("ssuk4", "010-2342-2yr2"))
        adapter.addItem(Person("ssuk5", "010-2342-2yr2"))
        adapter.addItem(Person("suk6", "010-2342-2yr2"))
        adapter.addItem(Person("ssuk6", "010-2342-2yr2"))
        adapter.addItem(Person("ssuk7", "010-2342-2yr2"))
        adapter.addItem(Person("ssuk8", "010-2342-2yr2"))
        adapter.addItem(Person("suk9", "010-2342-2yr2"))

        recyclerView.adapter = adapter

        //adapter.setOnItemClickListener(new OnPersonItemClickListener() {
        //            @Override
        //            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
        //                Person item = adapter.getItem(position);
        //
        //                Toast.makeText(getApplicationContext(), "아이템 선택됨 : " + item.getName(), Toast.LENGTH_LONG).show();
        //            }
        //        });

        adapter.setOnItemClickListner(object : OnPersonItemclickListner {
            override fun onItemClick(holder: PersonAdapter.viewHolder, view: View, position: Int) {
                val item = adapter.getItem(position)
                Toast.makeText(applicationContext, "아이템 선택됨 : " + item.name!!, Toast.LENGTH_LONG).show()
            }
        })
    }

    //리사이클러뷰는 껍데기 역할을 한다.
}
