package com.example.saferoads

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var editUsername: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPhone: EditText
    private lateinit var editAddress: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnEditProfile: Button
    private lateinit var btnSaveProfile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Initialize Views
        editUsername = findViewById(R.id.editUsername)
        editEmail = findViewById(R.id.editEmail)
        editPhone = findViewById(R.id.editPhone)
        editAddress = findViewById(R.id.editAddress)
        editPassword = findViewById(R.id.editPassword)
        btnEditProfile = findViewById(R.id.btnEditProfile)
        btnSaveProfile = findViewById(R.id.btnSaveProfile)

        // Get the saved user data from SharedPreferences
        val user = SharedPrefHelper.getUser(this)

        // Set user data in the EditText fields
        editUsername.setText(user.username)
        editEmail.setText(user.email)
        editPhone.setText(user.phone)
        editAddress.setText(user.address)
        editPassword.setText(user.password)

        // Disable the fields initially (read-only mode)
        disableFields()

        // Edit Profile button action (make fields editable)
        btnEditProfile.setOnClickListener {
            enableFields()
            btnEditProfile.isEnabled = false  // Disable the Edit button after editing
        }

        // Save Profile button action (save the updated profile information)
        btnSaveProfile.setOnClickListener {
            val updatedUsername = editUsername.text.toString()
            val updatedEmail = editEmail.text.toString()
            val updatedPhone = editPhone.text.toString()
            val updatedAddress = editAddress.text.toString()
            val updatedPassword = editPassword.text.toString()

            // Save the updated profile information back to SharedPreferences
            SharedPrefHelper.saveUser(this, updatedUsername, updatedEmail, updatedPhone, updatedAddress, updatedPassword)

            Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
            disableFields()  // Disable the fields again after saving
            btnEditProfile.isEnabled = true  // Re-enable the Edit button
        }
    }

    // Function to enable fields (edit mode)
    private fun enableFields() {
        editUsername.isFocusableInTouchMode = true
        editEmail.isFocusableInTouchMode = true
        editPhone.isFocusableInTouchMode = true
        editAddress.isFocusableInTouchMode = true
        editPassword.isFocusableInTouchMode = true
    }

    // Function to disable fields (view mode)
    private fun disableFields() {
        editUsername.isFocusableInTouchMode = false
        editEmail.isFocusableInTouchMode = false
        editPhone.isFocusableInTouchMode = false
        editAddress.isFocusableInTouchMode = false
        editPassword.isFocusableInTouchMode = false
    }
}
