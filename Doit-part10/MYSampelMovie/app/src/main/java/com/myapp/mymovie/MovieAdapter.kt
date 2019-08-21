package com.myapp.mymovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter :RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    var items = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var itemview = inflater.inflate(R.layout.movie_item, parent, false)

        return ViewHolder(itemview)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = items.get(position)
        holder.setItem(item)
    }

    fun additem(item: Movie){
        items.add(item)
    }

    fun setItem(items : ArrayList<Movie>) {
        this.items = items
    }

    fun getItem(position : Int) : Movie {
        return items[position]
    }

    inner class ViewHolder :RecyclerView.ViewHolder {

        var textview1:TextView
        var textview2 :TextView

        constructor(itemview :View) : super(itemview) {
            textview1 = itemView.findViewById(R.id.textView)
            textview2 = itemView.findViewById(R.id.textView2)
        }

        fun setItem(item : Movie){
            textview1.setText(item.movieNm)
            textview2.setText(item.audiCnt + " persons")
        }
    }
}