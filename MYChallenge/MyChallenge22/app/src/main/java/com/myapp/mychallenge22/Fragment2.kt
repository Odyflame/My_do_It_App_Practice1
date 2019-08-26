package com.myapp.mychallenge22

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Fragment2 : Fragment() {

    companion object {
        lateinit var callback : OnDatabaseCallback
        lateinit var recyclerView1: RecyclerView
        lateinit var adapter: BookAdapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        callback = activity as OnDatabaseCallback
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView =  inflater.inflate(R.layout.fragment_fragment2, container, false) as ViewGroup

        recyclerView1 = rootView.findViewById(R.id.recyclerView)
        recyclerView1.layoutManager = LinearLayoutManager(context)

        adapter = BookAdapter()
        recyclerView1.adapter = adapter
        var result = callback.selectAll()

        adapter.setOnItemClickListener( object : onBookItemClickListner{
            override fun onItemClick(holder: BookAdapter.ViewHolder, view: View, position: Int) {
                var item = adapter.getItem(position)

                Toast.makeText(context,"item selected : " + item.name.toString(),Toast.LENGTH_SHORT).show()
            }
        })

        var button2 = rootView.findViewById(R.id.button) as Button

        button2.setOnClickListener {
            var result = callback.selectAll()
            adapter.items = result
            adapter.notifyDataSetChanged()
        }

        return rootView
    }
}
