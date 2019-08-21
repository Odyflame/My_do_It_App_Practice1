package com.myapp.mydatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var database : SQLiteDatabase
    lateinit var tableName : String

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

    }

    fun createDatabase(name : String){
        println("createDatabase called")

        database = openOrCreateDatabase(name, Context.MODE_PRIVATE, null)

        println("create dataBase" + name)
    }

    fun createTable(name : String){
        println("createTable called")

        database?. let{
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

        database?.let {
            println("create database first")
            return
        }

        tableName?.let{
            println("create tableName first")
            return
        }

        database.execSQL("insert into" + tableName
                + "(name, age, moblie)"
                + "valeus"
                + "( 'john', 20, ' 010-1000-1000' )"
        )

        println("add recodes")

    }

    fun println(name :String){
        textView.append(name + "\n")
    }
}
