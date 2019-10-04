package com.myapp.mychallenge25

import android.view.View

interface OnPictureItemClickListner {
    fun onItemClick(holder : PictureAdapter.ViewHolder, view : View,position :Int )
}