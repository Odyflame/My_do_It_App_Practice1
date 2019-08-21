package com.myapp.mychallenge21

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DATAbaseHelper :SQLiteOpenHelper{
    override fun onCreate(db: SQLiteDatabase?) {
        println("onCreate called")

        val sql = ("create table if not exists emp("
                + " _id integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " writer text, "
                + " contents text)")

        db!!.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        println("onOpen called : " + oldVersion + " -> " + newVersion)

        if(newVersion > 1){
            db!!.execSQL("DROP TABLE IF EXISTS emp")
        }
    }

    companion object {

        val NAME = "employee.db"
        val VERSION = 1

    }
    constructor(context : Context) : super(context, NAME, null, VERSION)

    fun println(data :String) {
        Log.d("DATAbaseHelper", data)
    }
}