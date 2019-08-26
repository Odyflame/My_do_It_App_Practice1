package com.myapp.mychallenge22

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper:SQLiteOpenHelper {
    override fun onCreate(db: SQLiteDatabase?) {
        println("onCreate 호출됨")

        val sql = ("create table if not exists emp("
                + " _id integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " age integer, "
                + " mobile text)")

        db!!.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        println("onUpgrade 호출됨 : " + oldVersion + " -> " + newVersion)

        if (newVersion > 1) {
            db!!.execSQL("DROP TABLE IF EXISTS emp");
        }
    }

    companion object{
        var NAME = "employee.db"
        var VERSION = 1
    }

    constructor(context: Context) : super(context, NAME, null,VERSION) {

    }

    override fun onOpen(db: SQLiteDatabase) {
        println("onOpen 호출됨")
    }

    fun println(data: String) {
        Log.d("DatabaseHelper", data)
    }
}