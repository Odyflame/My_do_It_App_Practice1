package com.myapp.mydatabase

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var database : SQLiteDatabase
    lateinit var tableName : String

    lateinit var dbHelper : DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener {
            var dataBaseName = editText.text.toString()
            createDatabase(dataBaseName)
        }

        button2.setOnClickListener {
            tableName = editText2.text.toString()
            createTable(tableName)

            insertRecord()
        }

        button3.setOnClickListener {
            executeQuery()
        }
    }

    fun executeQuery(){
        println("executeQuery called")

        var cursor :Cursor = database.rawQuery("select _id, name, age, mobile from ddd", null)
        var recordCount = cursor.count
        println("record count : " + recordCount)

        for(i in 0..recordCount-1){
            cursor.moveToNext()

            println("record#" + i + " : " + cursor.getInt(0) + ", " + cursor.getString(1)+ ", " + cursor.getInt(2) + ", " + cursor.getString(3))
        }
        cursor.close()
    }

    fun createDatabase(name : String){
        println("createDatabase called")

        //database = openOrCreateDatabase(name, Context.MODE_PRIVATE, null)

        dbHelper = DataBaseHelper(this)
        database = dbHelper.writableDatabase

        println("create dataBase" + name)
    }

    fun createTable(name : String){
        println("createTable called" + name)

        if(database==null){
            println("create dataBase first")
            return
        }

        database.execSQL("create table if not exists " + name + "("
        + " _id integer PRIMARY KEY autoincrement, "
        +  " name text, "
        + " age integer, "
        + " mobile text)")

        println("create  Table")
    }

    fun insertRecord(){
        println("insertRecord called")

        if(null==database){
            println("create database first")
            return
        }

        if(tableName==null){
            println("create tableName first")
            return
        }

        database.execSQL("insert into " + tableName
                + "(name, age, mobile) "
                + " values  "
                + "( 'john' , 20, ' 010-1000-1000 ' )")

        println("add recodes")
    }

    fun println(name :String){
        textView.append(name + "\n")
    }
}
