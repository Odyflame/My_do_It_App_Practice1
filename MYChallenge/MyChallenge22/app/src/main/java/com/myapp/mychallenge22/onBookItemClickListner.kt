package com.myapp.mychallenge22

import android.view.View

interface onBookItemClickListner {
    fun onItemClick(holder : BookAdapter.ViewHolder, view : View, position: Int)
}