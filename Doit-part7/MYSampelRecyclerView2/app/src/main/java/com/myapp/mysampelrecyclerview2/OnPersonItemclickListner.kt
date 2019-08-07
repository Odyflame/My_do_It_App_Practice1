package com.myapp.mysampelrecyclerview2

import android.view.View

interface OnPersonItemclickListner{
    fun onItemClick(holder: PersonAdapter.viewHolder, view: View, position :Int)
}