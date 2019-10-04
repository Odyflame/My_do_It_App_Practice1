package com.myapp.mychallenge22

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
class BookAdapter : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    var items = ArrayList<Bookinfo>()
    lateinit var listener: onBookItemClickListner

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.ViewHolder {

        var inflater = LayoutInflater.from(parent.context)
        var itemView = inflater.inflate(R.layout.book_item, parent, false)

        return ViewHolder(itemView, this)
    }

    override fun getItemCount(): Int {

        return items.size
    }

    override fun onBindViewHolder(holder: BookAdapter.ViewHolder, position: Int) {
        var item = items[position]
        holder.setItem(item)
    }
    fun addItem(item: Bookinfo) {
        items.add(item)
    }

    fun getItem(position: Int): Bookinfo {
        return items[position]
    }

    fun setItem(position: Int, item: Bookinfo) {
        items[position] = item
    }

    fun setOnItemClickListener(listener: onBookItemClickListner) {
        this.listener = listener
    }

    fun onItemClick(holder: ViewHolder, view: View, position: Int) {
        if (listener != null) {
            listener.onItemClick(holder, view, position)
        }
    }

    inner class ViewHolder : RecyclerView.ViewHolder{

        lateinit var textView :TextView
        lateinit var textView2 :TextView
        lateinit var textView3 :TextView
        lateinit var imageView : ImageView

        constructor(itemView:View, listener: BookAdapter) : super(itemView) {

            textView = itemView.findViewById(R.id.textView)
            textView2 = itemView.findViewById(R.id.textView2)
            textView3 = itemView.findViewById(R.id.textView3)
            imageView = itemView.findViewById(R.id.imageView)

            itemView.setOnClickListener { view ->
                val position = adapterPosition

                if (listener != null) {
                    listener.onItemClick(holder = this@ViewHolder, view = view, position = position)
                }
            }

        }

        fun setItem(item  :Bookinfo){
            textView.text = item.name
            textView2.text = item.writer
            textView3.text = item.contents
        }
    }


}