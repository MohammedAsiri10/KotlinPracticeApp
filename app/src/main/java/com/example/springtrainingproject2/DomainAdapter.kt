package com.example.springtrainingproject2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DomainAdapter(private var domains: List<Domain>) :
    RecyclerView.Adapter<DomainAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val domainText: TextView = itemView.findViewById(R.id.domainTextView)
        private val statusText: TextView = itemView.findViewById(R.id.statusTextView)

        fun bind(domain: Domain) {
            // Display domain name and dead status
            domainText.text = domain.domain
            statusText.text = if (domain.isDead == "False") "Active" else "Inactive"
        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_domain, parent, false) // Ensure this layout exists
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(domains[position])
    }

    override fun getItemCount() = domains.size

    fun updateData(newDomains: List<Domain>) {
        domains = newDomains
        notifyDataSetChanged()
    }

}
