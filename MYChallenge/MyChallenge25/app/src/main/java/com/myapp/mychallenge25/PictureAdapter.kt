package com.myapp.mychallenge25

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import sun.font.LayoutPathImpl.getPath
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.R
import android.widget.TextView
import java.nio.file.Files.size
import androidx.annotation.NonNull
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import org.w3c.dom.Text


class PictureAdapter :RecyclerView.Adapter<PictureAdapter.ViewHolder>(), OnPictureItemClickListner {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureAdapter.ViewHolder {

        var inflater = LayoutInflater.from(parent.context)
        var itemView :View = inflater.inflate(R.layout.)

        return itemView
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = items[position]
    }

    override fun onItemClick(holder : ViewHolder, view :View, position :Int) {
        listner?.let{
            listner.onItemClick(holder,view, position )
        }
    }

    var items = ArrayList<Pictureinfo>()

    lateinit var listner :OnPictureItemClickListner

    inner class ViewHolder : RecyclerView.ViewHolder {

        lateinit var textview :TextView
        lateinit var textveiw2 :TextView
        lateinit var imageview :ImageView
        var options :BitmapFactory.Options = BitmapFactory.Options()

        constructor(itemView : View, listner: OnPictureItemClickListner) :super(itemView){
            textview = itemView.findViewById(R.id)
            options.inSampleSize = 12

            itemView.setOnClickListener { view->
                var position = adapterPosition
                listner?.let{listner.onItemClick(this@ViewHolder, view, position)}
            }
        }

        fun setItem(item : Pictureinfo){


        }

    }

}




/*
class PictureAdapter : RecyclerView.Adapter<PictureAdapter.ViewHolder>(),
    OnPictureItemClickListner {
    internal var items = ArrayList<Pictureinfo>()

    internal var listener: OnPictureItemClickListner? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val itemView = inflater.inflate(R.layout.picture_item, viewGroup, false)

        return ViewHolder(itemView, this)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = items[position]
        viewHolder.setItem(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    internal open class ViewHolder(itemView: View, listener: OnPictureItemClickListner?) :
        RecyclerView.ViewHolder(itemView) {
        var textView: TextView
        var textView2: TextView

        var imageView: ImageView

        var options = BitmapFactory.Options()

        init {

            textView = itemView.findViewById(R.id.textView)
            textView2 = itemView.findViewById(R.id.textView2)
            imageView = itemView.findViewById(R.id.imageView)

            options.inSampleSize = 12

            itemView.setOnClickListener { view ->
                val position = adapterPosition

                if (listener != null) {
                    listener!!.onItemClick(this@ViewHolder, view, position)
                }
            }
        }

        fun setItem(item: PictureInfo) {
            textView.setText(item.getDisplayName())
            textView2.setText(item.getDateAdded())

            val bitmap = BitmapFactory.decodeFile(item.getPath(), options)
            imageView.setImageBitmap(bitmap)
        }

    }

}*/
