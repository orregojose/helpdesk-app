package com.example.mesa_ayuda

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchTicketActivity : AppCompatActivity() {
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var backButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TicketAdapter
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_ticket)

        // Habilitar el botón de retroceso en la ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Consultar Ticket"

        searchEditText = findViewById(R.id.editTextSearch)
        searchButton = findViewById(R.id.buttonSearch)
        backButton = findViewById(R.id.buttonBack)
        recyclerView = findViewById(R.id.recyclerViewTickets)
        dbHelper = DatabaseHelper(this)

        setupRecyclerView()
        loadAllTickets()

        searchButton.setOnClickListener {
            val query = searchEditText.text.toString()
            if (query.isNotEmpty()) {
                val tickets = dbHelper.searchTickets(query)
                adapter.updateTickets(tickets)
            } else {
                loadAllTickets()
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = TicketAdapter(this, emptyList(), dbHelper)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun loadAllTickets() {
        val tickets = dbHelper.getAllTickets()
        adapter.updateTickets(tickets)
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