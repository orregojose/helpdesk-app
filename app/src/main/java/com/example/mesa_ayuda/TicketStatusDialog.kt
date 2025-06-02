package com.example.mesa_ayuda

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class TicketStatusDialog(
    private val currentStatus: TicketStatus,
    private val onStatusSelected: (TicketStatus) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Cambiar Estado del Ticket")
                .setItems(TicketStatus.values().map { it.name }.toTypedArray()) { _, which ->
                    val newStatus = TicketStatus.values()[which]
                    onStatusSelected(newStatus)
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
} 