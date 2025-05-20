package com.example.saferoads

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class AccidentReportActivity : AppCompatActivity() {

    private lateinit var editDate: EditText
    private lateinit var editTime: EditText
    private lateinit var editPlace: EditText
    private lateinit var editInjuries: EditText
    private lateinit var btnSave: Button

    companion object {
        private const val PREFS_NAME = "AccidentPrefs"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accident_report)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.dashboardToolbar)
        setSupportActionBar(toolbar)

        // Initialize views
        editDate = findViewById(R.id.editDate)
        editTime = findViewById(R.id.editTime)
        editPlace = findViewById(R.id.editPlace)
        editInjuries = findViewById(R.id.editInjuries)
        btnSave = findViewById(R.id.btnSave)

        // Listeners
        editDate.setOnClickListener { showDatePicker() }
        editTime.setOnClickListener { showTimePicker() }
        btnSave.setOnClickListener { saveReport() }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                editDate.setText(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                editTime.setText(selectedTime)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.show()
    }

    private fun saveReport() {
        val date = editDate.text.toString()
        val time = editTime.text.toString()
        val place = editPlace.text.toString()
        val injuries = editInjuries.text.toString()

        // Create the JSON object for the new report
        val report = JSONObject().apply {
            put("date", date)
            put("time", time)
            put("place", place)
            put("injuries", injuries)
            put("imageUri", "") // No image URI as the image upload is removed
        }

        // Get the current list of reports from SharedPreferences
        val sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val existingReports = sharedPrefs.getString("report_list", "[]")
        val reportList = JSONArray(existingReports)

        // Add the new report to the list
        reportList.put(report)

        // Save the updated list back to SharedPreferences
        sharedPrefs.edit().putString("report_list", reportList.toString()).apply()

        // Show a message that the report has been saved
        Toast.makeText(this, "Accident report saved", Toast.LENGTH_SHORT).show()

        // Close the activity after saving
        finish()
    }
}
