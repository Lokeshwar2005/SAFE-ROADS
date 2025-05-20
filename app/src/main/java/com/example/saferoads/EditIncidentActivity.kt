package com.example.saferoads

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.util.Calendar

class EditIncidentActivity : AppCompatActivity() {

    private lateinit var editDate: EditText
    private lateinit var editTime: EditText
    private lateinit var editPlace: EditText
    private lateinit var editInjuries: EditText
    private lateinit var btnSave: Button

    private var incidentPosition: Int = -1
    private lateinit var incidentToEdit: IncidentReport

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_incident)

        // Get the incident position and data passed from IncidentAdapter
        incidentPosition = intent.getIntExtra("incidentPosition", -1)
        incidentToEdit = intent.getParcelableExtra("incident")!!

        // Initialize views
        editDate = findViewById(R.id.editDate)
        editTime = findViewById(R.id.editTime)
        editPlace = findViewById(R.id.editPlace)
        editInjuries = findViewById(R.id.editInjuries)
        btnSave = findViewById(R.id.btnSaveIncident)

        // Populate fields with existing data
        editDate.setText(incidentToEdit.date)
        editTime.setText(incidentToEdit.time)
        editPlace.setText(incidentToEdit.place)
        editInjuries.setText(incidentToEdit.injuries)

        // Set up date picker dialog for date field
        editDate.setOnClickListener {
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

        // Set up time picker dialog for time field
        editTime.setOnClickListener {
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

        // Handle save button click
        btnSave.setOnClickListener {
            val updatedIncident = IncidentReport(
                date = editDate.text.toString(),
                time = editTime.text.toString(),
                place = editPlace.text.toString(),
                injuries = editInjuries.text.toString()
            )

            // Update the incident in the list
            val incidents = loadIncidents()
            incidents[incidentPosition] = updatedIncident
            saveIncidents(incidents)

            // Return to the previous screen
            Toast.makeText(this, "Incident updated", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun loadIncidents(): MutableList<IncidentReport> {
        val sharedPrefs = getSharedPreferences("AccidentPrefs", Context.MODE_PRIVATE)
        val reportList = JSONArray(sharedPrefs.getString("report_list", "[]"))
        val incidents = mutableListOf<IncidentReport>()
        for (i in 0 until reportList.length()) {
            val report = reportList.getJSONObject(i)
            incidents.add(
                IncidentReport(
                    date = report.getString("date"),
                    time = report.getString("time"),
                    place = report.getString("place"),
                    injuries = report.getString("injuries")
                )
            )
        }
        return incidents
    }

    private fun saveIncidents(incidents: MutableList<IncidentReport>) {
        val sharedPrefs = getSharedPreferences("AccidentPrefs", Context.MODE_PRIVATE)
        val reportList = JSONArray()

        incidents.forEach { incident ->
            val report = JSONObject()
            report.put("date", incident.date)
            report.put("time", incident.time)
            report.put("place", incident.place)
            report.put("injuries", incident.injuries)
            reportList.put(report)
        }

        sharedPrefs.edit().putString("report_list", reportList.toString()).apply()
    }
}
