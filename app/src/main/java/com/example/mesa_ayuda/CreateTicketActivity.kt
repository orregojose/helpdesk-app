package com.example.mesa_ayuda

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class CreateTicketActivity : AppCompatActivity() {
    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var prioritySpinner: Spinner
    private lateinit var createButton: Button
    private lateinit var backButton: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ticket)

        // Habilitar el botón de retroceso en la ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Generar Ticket"

        titleEditText = findViewById(R.id.editTextTicketTitle)
        descriptionEditText = findViewById(R.id.editTextTicketDescription)
        prioritySpinner = findViewById(R.id.spinnerPriority)
        createButton = findViewById(R.id.buttonCreateTicket)
        backButton = findViewById(R.id.buttonBack)
        dbHelper = DatabaseHelper(this)

        createButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val priority = prioritySpinner.selectedItem.toString()
            val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

            if (title.isNotEmpty() && description.isNotEmpty()) {
                val ticket = Ticket(
                    title = title,
                    description = description,
                    priority = priority,
                    status = TicketStatus.ABIERTO,
                    createdDate = currentDate,
                    assignedTo = ""
                )
                dbHelper.insertTicket(ticket)
                Toast.makeText(this, "Ticket creado exitosamente", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
} 