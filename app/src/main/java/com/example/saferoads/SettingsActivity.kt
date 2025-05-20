package com.example.saferoads

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {

    private lateinit var themeRadioGroup: RadioGroup
    private lateinit var radioLightTheme: RadioButton
    private lateinit var radioDarkTheme: RadioButton
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Initialize the views
        themeRadioGroup = findViewById(R.id.themeRadioGroup)
        radioLightTheme = findViewById(R.id.radioLightTheme)
        radioDarkTheme = findViewById(R.id.radioDarkTheme)
        btnLogout = findViewById(R.id.btnLogout)

        // Check the current theme and set the RadioButtons accordingly
        val currentMode = AppCompatDelegate.getDefaultNightMode()
        if (currentMode == AppCompatDelegate.MODE_NIGHT_NO) {
            radioLightTheme.isChecked = true
        } else if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
            radioDarkTheme.isChecked = true
        }

        // Handle theme change
        themeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioLightTheme -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                R.id.radioDarkTheme -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }
        }

        // Handle logout action
        btnLogout.setOnClickListener {
            // Redirect to login screen without clearing the data
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()  // Close the settings screen
        }
    }
}
