package com.example.mesa_ayuda

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var createTicketButton: Button
    private lateinit var searchTicketButton: Button
    private lateinit var ticketReportButton: Button
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createTicketButton = findViewById(R.id.buttonCreateTicket)
        searchTicketButton = findViewById(R.id.buttonSearchTicket)
        ticketReportButton = findViewById(R.id.buttonTicketReport)
        logoutButton = findViewById(R.id.buttonLogout)

        createTicketButton.setOnClickListener {
            startActivity(Intent(this, CreateTicketActivity::class.java))
        }

        searchTicketButton.setOnClickListener {
            startActivity(Intent(this, SearchTicketActivity::class.java))
        }

        ticketReportButton.setOnClickListener {
            startActivity(Intent(this, TicketReportActivity::class.java))
        }

        logoutButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
} 