package com.example.incomingcall_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class IncomingCallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_call)


        // Get the caller information from the intent extras
        val callerName = intent.getStringExtra("caller_name")
        val callerNumber = intent.getStringExtra("caller_number")

        // Display the caller information in the UI
        findViewById<TextView>(R.id.txtName).text = callerName
        findViewById<TextView>(R.id.txtPhone).text = callerNumber

        // Set up the buttons to accept or reject the call
        findViewById<Button>(R.id.btnOK).setOnClickListener {
            // Handle accepting the call
            finish()
        }

        findViewById<Button>(R.id.btnCancel).setOnClickListener {
            // Handle rejecting the call
            finish()
        }
    }
}