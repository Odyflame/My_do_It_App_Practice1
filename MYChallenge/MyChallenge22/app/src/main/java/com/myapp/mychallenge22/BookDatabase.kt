package com.myapp.mychallenge22

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class BookDatabase
constructor(private val context: Context) {
    companion object {

        /**
         * TAG for debugging
         */
        val TAG = "BookDatabase"

        /**
         * Singleton instance
         */
        private var database: BookDatabase? = null

        /**
         * database name
         */
        var DATABASE_NAME = "book.db"

        /**
         * table name for BOOK_INFO
         */
        var TABLE_BOOK_INFO = "BOOK_INFO"

        /**
         * version
         */
        var DATABASE_VERSION = 1


        fun getInstance(context: Context): BookDatabase {
            if (database == null) {
                database = BookDatabase(context)
            }

            return database as BookDatabase
        }
    }
    /**
     * Helper class defined
     */
    private var dbHelper: DatabaseHelper? = null

    /**
     * Database object
     */
    private var db: SQLiteDatabase? = null

    /**
     * open database
     *
     * @return
     */
    fun open(): Boolean {
        println("opening database [$DATABASE_NAME].")

        dbHelper = DatabaseHelper(context)
        db = dbHelper!!.writableDatabase

        return true
    }

    /**
     * close database
     */
    fun close() {
        println("closing database [$DATABASE_NAME].")
        db!!.close()
        database = null
    }

    /**
     * execute raw query using the input SQL
     * close the cursor after fetching any result
     *
     * @param SQL
     * @return
     */
    fun rawQuery(SQL: String): Cursor? {
        println("\nexecuteQuery called.\n")

        var c1: Cursor? = null
        try {
            c1 = db!!.rawQuery(SQL, null)
            println("cursor count : " + c1!!.getCount())
        }
        catch (ex: Exception) { Log.e(TAG, "Exception in executeQuery", ex) }

        return c1
    }

    fun execSQL(SQL: String): Boolean {
        println("\nexecute called.\n")

        try {
            Log.d(TAG, "SQL : $SQL")
            db!!.execSQL(SQL)
        } catch (ex: Exception) {
            Log.e(TAG, "Exception in executeQuery", ex)
            return false
        }

        return true
    }

    private inner class DatabaseHelper(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        override fun onCreate(_db: SQLiteDatabase) {
            // TABLE_BOOK_INFO
            println("creating table [$TABLE_BOOK_INFO].")

            // drop existing table
            val DROP_SQL = "drop table if exists $TABLE_BOOK_INFO"
            try { _db.execSQL(DROP_SQL) }
            catch (ex: Exception) { Log.e(TAG, "Exception in DROP_SQL", ex) }

            // create table
            val CREATE_SQL = ("create table " + TABLE_BOOK_INFO + "("
                    + "  _id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "  NAME TEXT, "
                    + "  AUTHOR TEXT, "
                    + "  CONTENTS TEXT, "
                    + "  CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
                    + ")")
            try { _db.execSQL(CREATE_SQL) }
            catch (ex: Exception) { Log.e(TAG, "Exception in CREATE_SQL", ex) }

            // insert 5 book records
            insertRecord(_db, "Do it! 안드로이드 앱 프로그래밍", "정재곤", "안드로이드 기본서로 이지스퍼블리싱 출판사에서 출판했습니다.")
            insertRecord(_db, "Programming Android", "Mednieks, Zigurd", "Oreilly Associates Inc에서 2011년 04월에 출판했습니다.")
            insertRecord(_db, "센차터치 모바일 프로그래밍", "이병옥,최성민 공저", "에이콘출판사에서 2011년 10월에 출판했습니다.")
            insertRecord(_db, "시작하세요! 안드로이드 게임 프로그래밍", "마리오 제흐너 저", "위키북스에서 2011년 09월에 출판했습니다.")
            insertRecord(_db, "실전! 안드로이드 시스템 프로그래밍 완전정복", "박선호,오영환 공저", "DW Wave에서 2010년 10월에 출판했습니다.")

        }

        override fun onOpen(db: SQLiteDatabase) {
            println("opened database [$DATABASE_NAME].")
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            println("Upgrading database from version $oldVersion to $newVersion.")

            if (oldVersion < 2) {   // version 1
            }
        }

        private fun insertRecord(
            _db: SQLiteDatabase,
            name: String,
            author: String,
            contents: String
        ) {
            try {
                _db.execSQL("insert into $TABLE_BOOK_INFO(NAME, AUTHOR, CONTENTS) values ('$name', '$author', '$contents');")
            } catch (ex: Exception) {
                Log.e(TAG, "Exception in executing insert SQL.", ex)
            }

        }

    }//inner class databasehelper

    fun insertRecord(name: String, author: String, contents: String) {
        try {
            db!!.execSQL("insert into $TABLE_BOOK_INFO(NAME, AUTHOR, CONTENTS) values ('$name', '$author', '$contents');")
        } catch (ex: Exception) {
            Log.e(TAG, "Exception in executing insert SQL.", ex)
        }

    }

    fun selectAll(): ArrayList<Bookinfo> {
        val result = ArrayList<Bookinfo>()

        try {
            val cursor = db!!.rawQuery("select NAME, AUTHOR, CONTENTS from $TABLE_BOOK_INFO", null)
            for (i in 0 until cursor.count) {
                cursor.moveToNext()
                val name = cursor.getString(0)
                val author = cursor.getString(1)
                val contents = cursor.getString(2)

                val info = Bookinfo(name, author, contents)
                result.add(info)
            }

        } catch (ex: Exception) {
            Log.e(TAG, "Exception in executing insert SQL.", ex)
        }

        return result
    }

    private fun println(msg: String) {
        Log.d(TAG, msg)
    }
}