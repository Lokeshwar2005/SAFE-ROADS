package com.example.saferoads

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EmergencyContactsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency_contacts)

        // Handle click event for Police Contact
        val policeContact = findViewById<TextView>(R.id.policeContact)
        policeContact.setOnClickListener {
            dialEmergencyNumber("100")  // Police number
        }

        // Handle click event for Fire Contact
        val fireContact = findViewById<TextView>(R.id.fireContact)
        fireContact.setOnClickListener {
            dialEmergencyNumber("101")  // Fire number
        }

        // Handle click event for Ambulance Contact
        val ambulanceContact = findViewById<TextView>(R.id.ambulanceContact)
        ambulanceContact.setOnClickListener {
            dialEmergencyNumber("102")  // Ambulance number
        }

        // Handle click event for Tourist Helpline Contact
        val touristHelplineContact = findViewById<TextView>(R.id.touristHelplineContact)
        touristHelplineContact.setOnClickListener {
            dialEmergencyNumber("1363")  // Tourist helpline number
        }

        // Handle click event for Road Accident Contact
        val roadAccidentContact = findViewById<TextView>(R.id.roadAccidentContact)
        roadAccidentContact.setOnClickListener {
            dialEmergencyNumber("1073")  // Road accident number
        }

        // Handle click event for Medical Helpline Contact
        val medicalHelplineContact = findViewById<TextView>(R.id.medicalHelplineContact)
        medicalHelplineContact.setOnClickListener {
            dialEmergencyNumber("104")  // Medical helpline number
        }
    }

    // Helper function to dial the emergency number
    private fun dialEmergencyNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        startActivity(intent)
    }
}
