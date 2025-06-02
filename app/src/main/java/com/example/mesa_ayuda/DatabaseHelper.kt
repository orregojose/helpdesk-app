package com.example.mesa_ayuda

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "helpdesk.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_TICKETS = "tickets"
        
        private const val KEY_ID = "_id"
        private const val KEY_TITLE = "title"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_STATUS = "status"
        private const val KEY_PRIORITY = "priority"
        private const val KEY_CREATED_DATE = "created_date"
        private const val KEY_ASSIGNED_TO = "assigned_to"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_TICKETS (
                $KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $KEY_TITLE TEXT,
                $KEY_DESCRIPTION TEXT,
                $KEY_STATUS TEXT,
                $KEY_PRIORITY TEXT,
                $KEY_CREATED_DATE TEXT,
                $KEY_ASSIGNED_TO TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TICKETS")
        onCreate(db)
    }

    fun insertTicket(ticket: Ticket): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(KEY_TITLE, ticket.title)
            put(KEY_DESCRIPTION, ticket.description)
            put(KEY_STATUS, ticket.status.toString())
            put(KEY_PRIORITY, ticket.priority)
            put(KEY_CREATED_DATE, ticket.createdDate)
            put(KEY_ASSIGNED_TO, ticket.assignedTo)
        }
        return db.insert(TABLE_TICKETS, null, values)
    }

    fun updateTicketStatus(ticketId: Long, newStatus: TicketStatus): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(KEY_STATUS, newStatus.toString())
        }
        return db.update(TABLE_TICKETS, values, "$KEY_ID = ?", arrayOf(ticketId.toString()))
    }

    fun getTicket(id: Long): Ticket? {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_TICKETS,
            null,
            "$KEY_ID = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        return if (cursor.moveToFirst()) {
            val ticket = Ticket(
                id = cursor.getLong(cursor.getColumnIndexOrThrow(KEY_ID)),
                title = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TITLE)),
                description = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DESCRIPTION)),
                status = TicketStatus.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(KEY_STATUS))),
                priority = cursor.getString(cursor.getColumnIndexOrThrow(KEY_PRIORITY)),
                createdDate = cursor.getString(cursor.getColumnIndexOrThrow(KEY_CREATED_DATE)),
                assignedTo = cursor.getString(cursor.getColumnIndexOrThrow(KEY_ASSIGNED_TO))
            )
            cursor.close()
            ticket
        } else {
            cursor.close()
            null
        }
    }

    fun getAllTickets(): List<Ticket> {
        val tickets = mutableListOf<Ticket>()
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_TICKETS,
            null,
            null,
            null,
            null,
            null,
            "$KEY_CREATED_DATE DESC"
        )

        if (cursor.moveToFirst()) {
            do {
                tickets.add(
                    Ticket(
                        id = cursor.getLong(cursor.getColumnIndexOrThrow(KEY_ID)),
                        title = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TITLE)),
                        description = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DESCRIPTION)),
                        status = TicketStatus.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(KEY_STATUS))),
                        priority = cursor.getString(cursor.getColumnIndexOrThrow(KEY_PRIORITY)),
                        createdDate = cursor.getString(cursor.getColumnIndexOrThrow(KEY_CREATED_DATE)),
                        assignedTo = cursor.getString(cursor.getColumnIndexOrThrow(KEY_ASSIGNED_TO))
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return tickets
    }

    fun searchTickets(query: String): List<Ticket> {
        val tickets = mutableListOf<Ticket>()
        val db = this.readableDatabase
        val selection = "$KEY_TITLE LIKE ? OR $KEY_DESCRIPTION LIKE ?"
        val searchQuery = "%$query%"
        val cursor = db.query(
            TABLE_TICKETS,
            null,
            selection,
            arrayOf(searchQuery, searchQuery),
            null,
            null,
            "$KEY_CREATED_DATE DESC"
        )

        if (cursor.moveToFirst()) {
            do {
                tickets.add(
                    Ticket(
                        id = cursor.getLong(cursor.getColumnIndexOrThrow(KEY_ID)),
                        title = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TITLE)),
                        description = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DESCRIPTION)),
                        status = TicketStatus.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(KEY_STATUS))),
                        priority = cursor.getString(cursor.getColumnIndexOrThrow(KEY_PRIORITY)),
                        createdDate = cursor.getString(cursor.getColumnIndexOrThrow(KEY_CREATED_DATE)),
                        assignedTo = cursor.getString(cursor.getColumnIndexOrThrow(KEY_ASSIGNED_TO))
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return tickets
    }
} 