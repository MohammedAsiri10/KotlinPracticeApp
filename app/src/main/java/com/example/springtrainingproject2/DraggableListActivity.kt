package com.example.springtrainingproject2

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class DraggableListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageListAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val imageList = mutableListOf(
        R.drawable.a, R.drawable.b, R.drawable.c,
        R.drawable.d, R.drawable.e, R.drawable.f,
        R.drawable.g, R.drawable.h, R.drawable.i,
        R.drawable.j,R.drawable.k,R.drawable.l,
        R.drawable.m,R.drawable.n,R.drawable.o,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draggable_list)

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        recyclerView = findViewById(R.id.recyclerView)

        adapter = ImageListAdapter(imageList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Drag and drop functionality
        val itemTouchHelper = ItemTouchHelper(ItemMoveCallback(adapter))
        itemTouchHelper.attachToRecyclerView(recyclerView)

        // Pull to refresh functionality
        swipeRefreshLayout.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                swipeRefreshLayout.isRefreshing = false // Hide after 2 seconds
            }, 2000)
        }
    }
}