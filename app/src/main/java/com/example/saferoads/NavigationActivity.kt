package com.example.saferoads

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class NavigationActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation) // Simple layout

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Explicitly check for permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1001
            )
        } else {
            // Permission is already granted, get the last known location
            getLastLocation()
        }
    }

    private fun getLastLocation() {
        // Explicitly check permission before accessing location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            try {
                fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        openGoogleMaps(location.latitude, location.longitude)
                    } else {
                        Toast.makeText(this, "Unable to get current location", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Error retrieving location.", Toast.LENGTH_SHORT).show()
                }
            } catch (e: SecurityException) {
                // Catch and handle any SecurityException
                Toast.makeText(this, "Permission denied: Unable to access location.", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Request permission if not granted
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1001
            )
        }
    }

    private fun openGoogleMaps(currentLat: Double, currentLng: Double) {
        val destinationLat = 12.9716 // Replace with your destination latitude
        val destinationLng = 77.5946 // Replace with your destination longitude

        val uri = ("https://www.google.com/maps/dir/?api=1" +
                "&origin=$currentLat,$currentLng" +
                "&destination=$destinationLat,$destinationLng" +
                "&travelmode=driving&dir_action=navigate").toUri()

        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")

        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        } else {
            Toast.makeText(this, "Google Maps is not installed!", Toast.LENGTH_LONG).show()
        }

        finish()
    }

    // Handle the result of permission request
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1001) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with getting the location
                getLastLocation()
            } else {
                // Permission denied
                Toast.makeText(this, "Permission denied. Cannot access location.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
