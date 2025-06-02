package com.example.mesa_ayuda

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TicketReportActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TicketAdapter
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var backButton: Button
    private lateinit var textViewOpenCount: TextView
    private lateinit var textViewInProgressCount: TextView
    private lateinit var textViewClosedCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_report)

        // Habilitar el botón de retroceso en la ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Informe de Tickets"

        dbHelper = DatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerViewTickets)
        backButton = findViewById(R.id.buttonBack)
        textViewOpenCount = findViewById(R.id.textViewOpenCount)
        textViewInProgressCount = findViewById(R.id.textViewInProgressCount)
        textViewClosedCount = findViewById(R.id.textViewClosedCount)
        
        setupRecyclerView()
        loadTickets()

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = TicketAdapter(this, emptyList(), dbHelper)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun loadTickets() {
        val tickets = dbHelper.getAllTickets()
        adapter.updateTickets(tickets)
        updateTicketCounts(tickets)
    }

    private fun updateTicketCounts(tickets: List<Ticket>) {
        var openCount = 0
        var inProgressCount = 0
        var closedCount = 0

        tickets.forEach { ticket ->
            when (ticket.status) {
                TicketStatus.ABIERTO -> openCount++
                TicketStatus.EN_PROCESO -> inProgressCount++
                TicketStatus.CERRADO -> closedCount++
                TicketStatus.PENDIENTE -> inProgressCount++
                TicketStatus.RESUELTO -> closedCount++
            }
        }

        textViewOpenCount.text = openCount.toString()
        textViewInProgressCount.text = inProgressCount.toString()
        textViewClosedCount.text = closedCount.toString()
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