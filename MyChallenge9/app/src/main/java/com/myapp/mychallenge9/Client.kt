package com.myapp.mychallenge9

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.*


class Client : Fragment() {

    var dateFormat = java.text.SimpleDateFormat("yyyy-mm-dd")

    var nameInput:EditText?=null
    var rootview :ViewGroup?=null
    var ageInput : EditText?=null
    var birthButton:Button?=null

    private var selectedDate : Date? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        rootview = inflater.inflate(R.layout.fragment_client, container, false) as ViewGroup

        nameInput = rootview!!.findViewById(R.id.nameInput)
        ageInput = rootview!!.findViewById(R.id.ageInput)

        birthButton = rootview!!.findViewById(R.id.birthButton)

        birthButton!!.setOnClickListener {
            //showDateDialog
            showDateDialog()
        }

        var saveButton:Button = rootview!!.findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            var nameStr :String = nameInput!!.text.toString()
            var ageStr :String = ageInput!!.text.toString()
            var birthStr :String = birthButton!!.text.toString()

            //this가 아니라 context로 해야 하는듯하다. 여기는 main이 아니라 프레그먼트라서 그런걸수도 있는듯하다.
            Toast.makeText(context, "이름 : " + nameStr + ", 나이 : " + ageStr + ", 생년월일 : " + birthStr, Toast.LENGTH_SHORT).show()

        }

        //set selected date using current date
        var curDate:Date = Date()
        setSelectedDate(curDate)

        return rootview
    }

    fun setSelectedDate(curDate : Date){
        selectedDate = curDate
        var selectedDateStr :String = dateFormat.format(curDate)
        birthButton!!.text = selectedDateStr
    }

    fun showDateDialog(){
        var birthDateStr : String = birthButton!!.text.toString()

        var calendar:Calendar = Calendar.getInstance()
        var curBirthDate = Date()

        try{
            curBirthDate = dateFormat.parse(birthDateStr)
        }catch (ex : Exception){
            ex.printStackTrace()
        }

        calendar.time = curBirthDate

        var curYear:Int = calendar.get(Calendar.YEAR)
        var curMonth:Int = calendar.get(Calendar.MONTH)
        var curDay:Int = calendar.get(Calendar.DAY_OF_MONTH)

        var dialog = DatePickerDialog(context!!, birthDateSetListner, curYear, curMonth, curDay)
        dialog.show()
    }

    private var birthDateSetListner = DatePickerDialog.OnDateSetListener { datePicker, Year, monthOfYear, dayOfMonth ->

        var selectedCalendar = java.util.Calendar.getInstance()
        selectedCalendar.set(Year, monthOfYear, dayOfMonth)

        var curdate = selectedCalendar.time
        setSelectedDate(curdate)
    }
}
