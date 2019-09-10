package com.myapp.mychallenge22_again

interface onDatabaseCallback {
    fun insert(name :String, author : String, contents : String)
    fun selectAll() : ArrayList<BookInfo>
}