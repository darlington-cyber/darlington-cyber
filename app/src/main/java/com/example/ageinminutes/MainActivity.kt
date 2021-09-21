package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import android.widget.Toast
import org.w3c.dom.Text
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//This is casting my button so I can Identify it in my code
        val btnDatePicker = findViewById<Button>(R.id.btnSelectDate)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
        }

    }
    fun clickDatePicker(view: View){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val dayOfMonth = myCalender.get(Calendar.DAY_OF_MONTH)

       val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->
                //Toast.makeText(this,"The Chosen year is $selectedYear, the month is $selectedMonth and day is $selectedDayOfMonth",Toast.LENGTH_LONG).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/${selectedYear}"
                val tvSelectedDate = findViewById<TextView>(R.id.tvselectedDate)
                val tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvageInMinutes)


                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                tvSelectedDate.text = selectedDate

                val theDate = simpleDateFormat.parse(selectedDate)

                //val selectedDateInMinutes = theDate.time!!/60000 //code for age in minutes
                val selectedDateInMinutes = theDate.time!!/86400000 // age in days

                val currentDate = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))

                //val currentDateToMinutes = currentDate!!.time/60000//  code for age in minutes
                val currentDateToMinutes = currentDate!!.time/86400000 // age in days

                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes

                tvSelectedDateInMinutes.text = differenceInMinutes.toString()


            },year,month,dayOfMonth)
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}