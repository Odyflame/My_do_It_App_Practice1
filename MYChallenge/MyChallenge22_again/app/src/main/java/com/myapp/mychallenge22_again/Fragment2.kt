package com.myapp.mychallenge22_again

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_fragment2.view.*

class Fragment2 : Fragment() {

    companion object{
        lateinit var callback :onDatabaseCallback
        lateinit var recyclerView: RecyclerView
        lateinit var adapter: BookAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_fragment2, container, false) as ViewGroup

        recyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = BookAdapter()
        recyclerView.adapter = adapter
        var result = callback.selectAll()

        adapter.setOnItemClickListener()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = activity as onDatabaseCallback
    }

}
