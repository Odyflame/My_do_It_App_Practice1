package com.myapp.mydatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataBaseHelper :SQLiteOpenHelper {

    companion object {
        val NAME :String = "employee.db"
        var VERSIOn :Int = 1
    }

    constructor(context : Context) : super(context, NAME, null, VERSIOn)

    override fun onCreate(db: SQLiteDatabase?) {
        println("onCreate called ")

        db!!.execSQL("create table if not exists emp("
                + " _id integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " age integer, "
                + " moblie text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        println("onUpgrade called " + oldVersion + " -> " + newVersion)

        if(newVersion>1) {
            db!!.execSQL("DROP TABLE IF EXISTS emp")
        }
    }

    override fun onOpen(db: SQLiteDatabase?) {
        println("onOpen called")
    }

    fun println(name :String){
        Log.d("DatabaseHelper", name)
    }

}