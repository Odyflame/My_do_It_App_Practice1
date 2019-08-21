package com.myapp.myprovider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class PersonProvider : ContentProvider() {

    private val AUTHORITY = "com.myapp.myprovider"
    private val BASE_PATH = "person"
    val CONTENT_URI = Uri.parse("content://$AUTHORITY/$BASE_PATH")

    private val PERSONS = 1
    private val PERSON_ID = 2

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init
    {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, PERSONS)
        uriMatcher.addURI(AUTHORITY, "$BASE_PATH/#", PERSON_ID)
    }

    lateinit var database: SQLiteDatabase

    override fun insert(uri: Uri, contentValues: ContentValues?): Uri? {
        val id = database.insert(DatabaseHelper.TABLE_NAME, null, contentValues)

        if (id > 0) {
            val _uri = ContentUris.withAppendedId(CONTENT_URI, id)
            context!!.contentResolver.notifyChange(_uri, null)
            return _uri
        }

        throw SQLException("추가 실패 -> URI :$uri")
    }

    override fun query(uri: Uri, p1: Array<out String>?, p2: String?, p3: Array<out String>?, p4: String?): Cursor? {
        var cursor :Cursor? = null
        when(uriMatcher.match(uri)){
            PERSONS -> cursor = database!!.query(DatabaseHelper.TABLE_NAME,
                DatabaseHelper.ALL_COLUMNS,
                p2,
                null,
                null,
                null,
                DatabaseHelper.PERSON_NAME + " ASC")
            else -> throw IllegalAccessException("unknown url " + uri)
        }

        cursor.setNotificationUri(context!!.contentResolver, uri)
        return cursor
    }

    override fun onCreate(): Boolean {
        var helper:DatabaseHelper = DatabaseHelper(context!!)
        database = helper.writableDatabase
        return true
    }

    override fun update(uri: Uri, contentValues: ContentValues?, s: String?, strings: Array<out String>?): Int {
        var count = 0
        when (uriMatcher.match(uri)) {
            PERSONS -> count = database!!.update(DatabaseHelper.TABLE_NAME, contentValues, s, strings)
            else -> throw IllegalArgumentException("알 수 없는 URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)

        return count
    }

    override fun delete(uri: Uri, s: String?, strings: Array<out String>?): Int {
        var count = 0
        when (uriMatcher.match(uri)) {
            PERSONS -> count = database!!.delete(DatabaseHelper.TABLE_NAME, s, strings)
            else -> throw IllegalArgumentException("알 수 없는 URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)

        return count
    }

    override fun getType(uri: Uri): String? {
        when(uriMatcher.match(uri)){
            PERSONS -> return "vnd.android.cursor.dir/persons"
            else -> throw IllegalArgumentException("알 수 없는 URI " + uri)
        }
    }
}