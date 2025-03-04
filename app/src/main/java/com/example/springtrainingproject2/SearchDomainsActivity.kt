package com.example.springtrainingproject2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class SearchDomainsActivity : AppCompatActivity() {

    private lateinit var domainInput: EditText
    private lateinit var zoneInput: EditText
    private lateinit var searchButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DomainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_domains)

        domainInput = findViewById(R.id.domainInput)
        zoneInput = findViewById(R.id.zoneInput)
        searchButton = findViewById(R.id.searchButton)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = DomainAdapter(emptyList())
        recyclerView.adapter = adapter

        searchButton.setOnClickListener {
            val domain = domainInput.text.toString().trim()
            val zone = zoneInput.text.toString().trim()

            if (domain.isNotEmpty() && zone.isNotEmpty()) {
                searchDomains(domain, zone)
            } else {
                Toast.makeText(this, "Please enter both domain and zone", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun searchDomains(domain: String, zone: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.domainsdb.info/v1/")  // Base URL without query params
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(DomainApiService::class.java)
        service.searchDomains(domain, zone).enqueue(object : Callback<DomainResponse> {
            override fun onResponse(call: Call<DomainResponse>, response: Response<DomainResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        adapter.updateData(it.domains)
                        if (it.domains.isEmpty()) {
                            Toast.makeText(this@SearchDomainsActivity,
                                "No domains found",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this@SearchDomainsActivity,
                        "Error: ${response.code()} - ${response.message()}",
                        Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<DomainResponse>, t: Throwable) {
                Toast.makeText(this@SearchDomainsActivity,
                    "Network error: ${t.message}",
                    Toast.LENGTH_LONG).show()
            }
        })
    }
}
