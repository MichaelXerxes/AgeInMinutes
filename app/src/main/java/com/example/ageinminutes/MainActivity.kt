package com.example.ageinminutes

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button:Button=findViewById(R.id.buttonID)

        button.setOnClickListener {view ->
            clickDatePicker(view)
        }
    }
    fun clickDatePicker(view: View){
        val myCalendar=Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd=DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener
            { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,"Date Picker Works",Toast.LENGTH_LONG).show()
                val selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                val textView:TextView=findViewById(R.id.tvSelectionDate)

                textView.setText(selectedDate)

                val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate=sdf.parse(selectedDate) ///Date class theDate
                val selectedDateInMinutes=theDate!!.time /60000

                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes=currentDate!!.time /60000

                val difrentsInMinutes=currentDateInMinutes-selectedDateInMinutes


                val displayView:TextView=findViewById(R.id.tvSelectDateInMinutes)
                val displayViewDays:TextView=findViewById(R.id.tvAgeInDays)
                displayView.setText(difrentsInMinutes.toString())
                displayViewDays.setText((difrentsInMinutes/(24*60)).toString())

            }
            ,year
            ,month
            ,day)
        // setMaxDate - day  -> set calendar max date for yesterday
        dpd.datePicker.setMaxDate(Date().time-86400000)//86400000 milliseconds per day
        dpd.show()
    }

}