package com.myapp.mychallenge22_again

import android.view.View

interface onBookItemClickListener {
    fun onItemClick(holder: BookAdapter.ViewHolder, view : View, position : Int)
}