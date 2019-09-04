package com.myapp.mychallenge22

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_fragment1.*


class Fragment1 : Fragment() {

    lateinit var database : SQLiteDatabase
    lateinit var dbHelper  :DatabaseHelper

    lateinit var tableName:String
    lateinit var mybookinfo : Bookinfo

    lateinit var callback : OnDatabaseCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)

        callback = activity as OnDatabaseCallback
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView = inflater.inflate(R.layout.fragment_fragment1, container, false) as ViewGroup

        var button1 = rootView.findViewById(R.id.button) as Button

        button1.setOnClickListener {

            /*createDatabase(editText.text.toString())
            tableName = editText2.getText().toString()
            createTable(tableName)

            insertRecord(editText.text.toString(), editText2.text.toString(), editText3.text.toString())*/

            callback.insert(editText.text.toString(), editText2.text.toString(), editText3.text.toString())
            Toast.makeText(context, "added bookinfo.", Toast.LENGTH_SHORT).show()
        }

        return rootView
    }

    fun createDatabase(name :String){
        if(database==null) return

        dbHelper = DatabaseHelper(this.context!!)
        database = dbHelper.writableDatabase

        println("create database : " + name)
    }

    fun createTable(name:String){
        if(database==null) return

        database.execSQL("create table if not exists " + name + "("
                + " _id integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " writer text, "
                + " contents text)");

        println("테이블 생성함 : " + name);
    }

    fun insertRecord(name :String, writer : String, contents : String){
        if(database==null || tableName==null) return

        database.execSQL("insert into " + tableName
                + "(name, age, mobile) "
                + " values "
                + "('$name', '$writer', '$contents')")

        mybookinfo = Bookinfo(name, writer, contents)
    }

    fun println(data :String){
        Log.d("Fragment1" , data)
    }
}
