package com.myapp.myprovider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper :SQLiteOpenHelper {
    companion object {
        private val DATABASE_NAME = "person.db"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "person"
        val PERSON_ID = "_id"
        val PERSON_NAME = "name"
        val PERSON_AGE = "age"
        val PERSON_MOBILE = "mobile"

        val ALL_COLUMNS = arrayOf(PERSON_ID, PERSON_NAME, PERSON_AGE, PERSON_MOBILE)

        private val CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                PERSON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PERSON_NAME + " TEXT, " +
                PERSON_AGE + " INTEGER, " +
                PERSON_MOBILE + " TEXT" +
                ")"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(p0)
    }

    constructor(context : Context) :super(context, DATABASE_NAME, null, DATABASE_VERSION)
}