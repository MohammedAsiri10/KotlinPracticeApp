package com.example.springtrainingproject2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // list of buttons
        val buttonList = listOf(
            ButtonItem("Image Slider"),
            ButtonItem("Feature 2"),
            ButtonItem("Feature 3"),
            ButtonItem("Feature 4"),
            ButtonItem("Feature 5")
        )

        // Initialize RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ButtonAdapter(buttonList) { feature ->
            val intent = if (feature == "Image Slider") {
                Intent(this, ImageSliderActivity::class.java)
            } else {
                Intent(this, FeatureActivity::class.java)
            }
            intent.putExtra("feature_name", feature)
            startActivity(intent)
        }
    }
}