package com.example.saferoads

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TrafficUpdatesActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var locationEditText: EditText
    private lateinit var checkTrafficButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traffic_updates)

        webView = findViewById(R.id.webView)
        locationEditText = findViewById(R.id.locationEditText)
        checkTrafficButton = findViewById(R.id.checkTrafficButton)

        // Enable JavaScript for interactive maps
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        checkTrafficButton.setOnClickListener {
            val location = locationEditText.text.toString().trim()

            if (location.isEmpty()) {
                Toast.makeText(this, "Please enter a location!", Toast.LENGTH_SHORT).show()
            } else {
                showTrafficInWebView(location)
            }
        }
    }

    private fun showTrafficInWebView(location: String) {
        val googleMapsUrl = "https://www.google.com/maps/search/?api=1&query=$location&layer=t"
        webView.loadUrl(googleMapsUrl)
    }
}