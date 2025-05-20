package com.example.saferoads

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject

class ReportedIncidentsActivity : AppCompatActivity() {
    private lateinit var adapter: IncidentAdapter
    private lateinit var incidents: MutableList<IncidentReport>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reported_incidents)

        val listView = findViewById<ListView>(R.id.incidentsListView)
        incidents = loadIncidents()

        // Set up the adapter to display the incidents
        adapter = IncidentAdapter(this, incidents)
        listView.adapter = adapter


    }

    // Removed file picker logic as it's no longer necessary
    // Instead of picking an image, we now add a new incident without image URI
    private fun addNewIncident() {
        val newIncident = IncidentReport(
            date = "2025-04-23", // You can use actual date
            time = "14:00",      // You can use actual time
            place = "Someplace",  // You can use actual place
            injuries = "None"    // You can use actual injury info
            // imageUri is no longer needed
        )

        incidents.add(newIncident)
        adapter.notifyDataSetChanged() // Notify the adapter that the data has changed

        // Save the new list to SharedPreferences
        saveIncidents()
    }

    // Load incidents from SharedPreferences (without image URI)
    private fun loadIncidents(): MutableList<IncidentReport> {
        val sharedPrefs = getSharedPreferences("AccidentPrefs", Context.MODE_PRIVATE)
        val reportList = JSONArray(sharedPrefs.getString("report_list", "[]"))
        val incidents = mutableListOf<IncidentReport>()

        // Iterate through the JSON array and convert it to IncidentReport objects
        for (i in 0 until reportList.length()) {
            val report = reportList.getJSONObject(i)
            incidents.add(
                IncidentReport(
                    date = report.getString("date"),
                    time = report.getString("time"),
                    place = report.getString("place"),
                    injuries = report.getString("injuries")
                    // imageUri is no longer required
                )
            )
        }
        return incidents
    }

    // Save the list of incidents to SharedPreferences (without image URI)
    private fun saveIncidents() {
        val sharedPrefs = getSharedPreferences("AccidentPrefs", Context.MODE_PRIVATE)
        val reportList = JSONArray()

        // Convert the list of incidents to a JSON array without imageUri
        incidents.forEach { incident ->
            val report = JSONObject()
            report.put("date", incident.date)
            report.put("time", incident.time)
            report.put("place", incident.place)
            report.put("injuries", incident.injuries)
            // Do not include imageUri
            reportList.put(report)
        }

        // Save the JSON array as a string in SharedPreferences
        sharedPrefs.edit().putString("report_list", reportList.toString()).apply()
    }
}
