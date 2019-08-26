package com.myapp.mychallenge22

interface OnDatabaseCallback {
    fun insert(name : String, author : String, contents: String)
    fun selectAll() : ArrayList<Bookinfo>
}