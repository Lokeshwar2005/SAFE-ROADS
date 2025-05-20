package com.example.saferoads

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val phone = findViewById<EditText>(R.id.phone)
        val address = findViewById<EditText>(R.id.address)
        val password = findViewById<EditText>(R.id.password)
        val confirmPassword = findViewById<EditText>(R.id.confirmPassword)
        val signupButton = findViewById<Button>(R.id.signupButton)

        signupButton.setOnClickListener {
            val user = username.text.toString()
            val userEmail = email.text.toString()
            val userPhone = phone.text.toString()
            val userAddress = address.text.toString()
            val pass = password.text.toString()
            val confirmPass = confirmPassword.text.toString()

            if (user.isNotEmpty() && userEmail.isNotEmpty() && userPhone.isNotEmpty() &&
                userAddress.isNotEmpty() && pass.isNotEmpty() && pass == confirmPass) {

                // Save the user details in SharedPreferences
                SharedPrefHelper.saveUser(this, user, userEmail, userPhone, userAddress, pass)

                Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
