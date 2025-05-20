package com.example.saferoads

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Handling click events for various sections

        // Accident Reporting Image Click
        val accidentImage = view.findViewById<ImageView>(R.id.accidentImageView)
        accidentImage.setOnClickListener {
            val intent = Intent(requireContext(), AccidentReportActivity::class.java)
            startActivity(intent)
        }

        // Navigation Image Click
        val navigationImage = view.findViewById<ImageView>(R.id.imageNavigation)
        navigationImage.setOnClickListener {
            val intent = Intent(requireContext(), NavigationActivity::class.java)
            startActivity(intent)
        }

        // Incidents Reported Image Click
        val incidentImage = view.findViewById<ImageView>(R.id.imageViewIncidentsReported)
        incidentImage.setOnClickListener {
            val intent = Intent(requireContext(), ReportedIncidentsActivity::class.java)
            startActivity(intent)
        }

        // Emergency Contacts Image Click
        val emergencyContactsImage = view.findViewById<ImageView>(R.id.imageViewEmergencyContacts)
        emergencyContactsImage.setOnClickListener {
            val intent = Intent(requireContext(), EmergencyContactsActivity::class.java)
            startActivity(intent)
        }

        // Road Safety Image Click (Newly Added)
        val roadSafetyImage = view.findViewById<ImageView>(R.id.imageViewRoadSafety)
        roadSafetyImage.setOnClickListener {
            // Navigate to Road Safety Activity
            val intent = Intent(requireContext(), RoadSafetyActivity::class.java)
            startActivity(intent)
        }



        // Traffic Updates Image Click
        val trafficUpdatesImage = view.findViewById<ImageView>(R.id.imageViewTrafficUpdates)
        trafficUpdatesImage.setOnClickListener {
            val intent = Intent(requireContext(), TrafficUpdatesActivity::class.java)
            startActivity(intent)
        }

        // Profile Button Click
        val btnProfile: Button = view.findViewById(R.id.btnProfile)
        btnProfile.setOnClickListener {
            // Navigate to Profile Activity
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(intent)
        }

        // Settings Button Click
        val btnSettings: Button = view.findViewById(R.id.btnSettings)
        btnSettings.setOnClickListener {
            // Navigate to Settings Activity
            val intent = Intent(requireContext(), SettingsActivity::class.java)
            startActivity(intent)
        }



        return view
    }
}
