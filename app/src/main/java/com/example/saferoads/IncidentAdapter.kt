package com.example.saferoads

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import org.json.JSONArray
import org.json.JSONObject

class IncidentAdapter(private val context: Context, private val incidentList: MutableList<IncidentReport>) :
    ArrayAdapter<IncidentReport>(context, 0, incidentList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_incident_report, parent, false)

        val incident = incidentList[position]

        val dateText = view.findViewById<TextView>(R.id.incidentDate)
        val timeText = view.findViewById<TextView>(R.id.incidentTime)
        val placeText = view.findViewById<TextView>(R.id.incidentPlace)
        val injuriesText = view.findViewById<TextView>(R.id.incidentInjuries)
        val deleteButton = view.findViewById<Button>(R.id.deleteButton)
        val editButton = view.findViewById<Button>(R.id.editButton)

        // Set text for the incident details (Date, Time, Place, Injuries)
        dateText.text = "Date: ${incident.date}"
        timeText.text = "Time: ${incident.time}"
        placeText.text = "Place: ${incident.place}"
        injuriesText.text = "Injuries: ${incident.injuries}"

        // Handle delete action
        deleteButton.setOnClickListener {
            deleteReport(position)
        }

        // Handle edit action
        editButton.setOnClickListener {
            editReport(incident, position)
        }

        return view
    }

    // Function to delete a report from the list and update SharedPreferences
    private fun deleteReport(position: Int) {
        incidentList.removeAt(position)
        notifyDataSetChanged()

        // Update SharedPreferences with the modified list
        updateSharedPreferences()
    }

    // Function to edit a report (opens an EditActivity)
    private fun editReport(incident: IncidentReport, position: Int) {
        // Here we can open an activity or dialog to edit the incident's details
        val intent = Intent(context, EditIncidentActivity::class.java)
        intent.putExtra("incidentPosition", position)
        intent.putExtra("incident", incident)
        context.startActivity(intent)
    }

    // Function to update SharedPreferences after modifications
    private fun updateSharedPreferences() {
        val sharedPrefs = context.getSharedPreferences("AccidentPrefs", Context.MODE_PRIVATE)
        val reportList = JSONArray()
        incidentList.forEach { incident ->
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
