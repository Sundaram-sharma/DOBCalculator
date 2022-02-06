package com.dobcalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_main)
        /*This is implemented by android studio it self when we select the Basic Activity while creating the project.*/
        setSupportActionBar(toolbar)

        // OnClickListener is set to the button for launching the DatePicker Dialog.
        btnDatePicker.setOnClickListener { view ->
            clickDataPicker(view)
        }
    }

    fun clickDataPicker(view: View) {

        val c = Calendar.getInstance()
        val year =
            c.get(Calendar.YEAR) // Returns the value of the given calendar field. This indicates YEAR
        val month = c.get(Calendar.MONTH) // This indicates the Month
        val day = c.get(Calendar.DAY_OF_MONTH) // This indicates the Day


        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"

                // Selected date it set to the TextView to make it visible to user.
                tvSelectedDate.setText(selectedDate)


                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                // The formatter will parse the selected date in to Date object
                // so we can simply get date in to milliseconds.
                val theDate = sdf.parse(selectedDate)

                val selectedDateToMinutes = theDate!!.time / 60000

                // Here we have parsed the current date with the Date Formatter which is used above.
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                // Current date in to minutes.
                val currentDateToMinutes = currentDate!!.time / 60000


                val differenceInMinutes = (currentDateToMinutes - selectedDateToMinutes) / (60*24)


                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())
            },
            year,
            month,
            day
        )


        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show() // It is used to show the datePicker Dialog.
    }
}