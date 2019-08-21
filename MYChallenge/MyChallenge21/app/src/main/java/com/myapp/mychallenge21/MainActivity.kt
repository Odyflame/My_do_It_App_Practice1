package com.myapp.mychallenge21

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dbHelper : DATAbaseHelper
    lateinit var database : SQLiteDatabase

    var tableName :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            createDataBase(editText.text.toString())
            createTable(editText2.text.toString())
            insertRecord(editText.text.toString(), editText2.text.toString() ,editText3.text.toString())

            Toast.makeText(this, "success!!", Toast.LENGTH_SHORT).show()
        }
    }


    fun createDataBase(name :String){
        println("createDataBase called")

        dbHelper = DATAbaseHelper(this)
        database = dbHelper.writableDatabase

        println("database created" + name)
    }

    fun createTable(name :String){
        println("createTable called")

        if(database==null) return

        database.execSQL("create table if not exists " + name + "("
        + " _id integer PRIMARY KEY autoincrement, "
        + " name text, "
        + " writer text, "
        + " contents text)")

        println("create Table : " + name)
    }

    fun insertRecord(name :String, writer : String, contents :String){
        println("insertRecord called")

        if(database==null) return
        if(tableName==null) return

        database.execSQL("insert into " + tableName
        + "(name, writer, contents) "
        + " values "
        + "('$name', '$writer', '$contents' )")

        println("added record")

    }
}
