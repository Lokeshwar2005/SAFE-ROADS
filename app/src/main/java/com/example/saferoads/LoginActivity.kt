package com.example.saferoads

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val signupRedirect = findViewById<Button>(R.id.signupRedirect)

        loginButton.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()

            if (SharedPrefHelper.isValidUser(this, user, pass)) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }

        signupRedirect.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
