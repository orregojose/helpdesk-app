package com.example.mesa_ayuda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class TicketAdapter(
    private val activity: FragmentActivity,
    private var tickets: List<Ticket>,
    private val dbHelper: DatabaseHelper
) : RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    class TicketViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.textTitle)
        val descriptionText: TextView = view.findViewById(R.id.textDescription)
        val statusText: TextView = view.findViewById(R.id.textStatus)
        val priorityText: TextView = view.findViewById(R.id.textPriority)
        val changeStatusButton: MaterialButton = view.findViewById(R.id.buttonChangeStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ticket, parent, false)
        return TicketViewHolder(view)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticket = tickets[position]
        holder.titleText.text = ticket.title
        holder.descriptionText.text = ticket.description
        holder.statusText.text = "Estado: ${ticket.status.name}"
        holder.priorityText.text = "Prioridad: ${ticket.priority}"

        holder.changeStatusButton.setOnClickListener {
            showStatusDialog(ticket)
        }
    }

    override fun getItemCount() = tickets.size

    private fun showStatusDialog(ticket: Ticket) {
        TicketStatusDialog(ticket.status) { newStatus ->
            dbHelper.updateTicketStatus(ticket.id, newStatus)
            val updatedTicket = dbHelper.getTicket(ticket.id)
            if (updatedTicket != null) {
                val position = tickets.indexOf(ticket)
                if (position != -1) {
                    (tickets as MutableList<Ticket>)[position] = updatedTicket
                    notifyItemChanged(position)
                }
            }
        }.show(activity.supportFragmentManager, "statusDialog")
    }

    fun updateTickets(newTickets: List<Ticket>) {
        tickets = newTickets
        notifyDataSetChanged()
    }
} 