package com.example.springtrainingproject2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FeatureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)

        // Get the button name from the intent
        val featureName = intent.getStringExtra("feature_name")

        // Set the text to display the clicked button name
        val textView = findViewById<TextView>(R.id.textView)
        val selectedMessage = getString(R.string.Selected, featureName ?: "Unknown")
        textView.text = selectedMessage
    }
}