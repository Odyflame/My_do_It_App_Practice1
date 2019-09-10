package com.myapp.mychallenge22_again

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class BookDatabase
constructor(context: Context){
    companion object{
        val TAG = "BookDatabase"
        private var database :BookDatabase ? =null
        var DATABASE_NAME = "book.db"
        var TABLE_BOOK_INFO = "BOOK_INFO"
        var DATABASE_VERSION = 1

        fun getinstance(context: Context) :BookDatabase{
            if(database == null){
                database = BookDatabase(context)
            }

            return database as BookDatabase
        }
    }

    private var dbHelper : DataBaseHelper ?= null
    private var db :SQLiteDatabase ?= null

    private inner class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
        override fun onCreate(db: SQLiteDatabase) {

            val DROP_SQL = "drop table if exists $TABLE_BOOK_INFO"
            try{ db.execSQL(DROP_SQL) }
            catch (ex :Exception) {Log.e(TAG, "Exception in DROP_SQL", ex) }

            val CREATE_SQL = ("create table " + TABLE_BOOK_INFO + "("
                    + "  _id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "  NAME TEXT, "
                    + "  AUTHOR TEXT, "
                    + "  CONTENTS TEXT, "
                    + "  CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
                    + ")")

            try { db.execSQL(CREATE_SQL) }
            catch (ex: Exception) { Log.e(TAG, "Exception in CREATE_SQL", ex) }
        }

        override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, p2: Int) {
            if(oldVersion < 2){}
        }

        private fun insertRecord(db :SQLiteDatabase, name :String, author :String, contents :String){
            try{
                db.execSQL("insert into $TABLE_BOOK_INFO(NAME, AUTHOR, CONTENTS) values ('$name', '$author', '$contents')")
            }catch (ex :Exception){
                Log.e(TAG, "Exception in executing insert SQL.", ex)
            }
        }

    }//inner class databaseHelper

    fun insertRecord(name :String, author :String, contents :String){
        try{
            db!!.execSQL("insert into $TABLE_BOOK_INFO(NAME, AUTHOR, CONTENTS) values ('$name' ,'$author', '$contents')")
        }catch(ex :Exception){
            Log.e(TAG, "Exception in executing insert SQL.", ex)
        }
    }

    fun selectAll() :ArrayList<BookInfo>{
        val result = ArrayList<BookInfo>()

        try{
            val cursor = db!!.rawQuery("select NAME, AUTHOR, CONTENTS from $TABLE_BOOK_INFO", null)
            for(i in 0 until cursor.count){
                cursor.moveToNext()
                val name = cursor.getString(0)
                val author = cursor.getString(1)
                val contents = cursor.getString(2)

                val info = BookInfo(name, author, contents)
                result.add(info)
            }
        }catch (ex :Exception){
            Log.e(TAG, "Exception in executing insert SQL.", ex)
        }

        return result
    }


}