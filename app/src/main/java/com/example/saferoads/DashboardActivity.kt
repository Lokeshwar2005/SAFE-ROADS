package com.example.saferoads

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val toolbar = findViewById<Toolbar>(R.id.dashboardToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Set default fragment as HomeFragment
        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, homeFragment)
            .commit()
    }

}
