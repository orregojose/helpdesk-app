package com.example.mesa_ayuda

enum class TicketStatus {
    ABIERTO,
    EN_PROCESO,
    PENDIENTE,
    RESUELTO,
    CERRADO
}

data class Ticket(
    val id: Long = 0,
    val title: String,
    val description: String,
    var status: TicketStatus,
    val priority: String,
    val createdDate: String = "",
    val assignedTo: String = ""
) 