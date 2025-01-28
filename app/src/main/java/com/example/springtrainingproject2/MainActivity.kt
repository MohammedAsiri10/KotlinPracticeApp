package com.example.springtrainingproject2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logo: ImageView = findViewById(R.id.app_logo)
        val appName: TextView = findViewById(R.id.app_name)

        // Load animations
        val scaleInLogo = AnimationUtils.loadAnimation(this, R.anim.scale_in_logo)
        val scaleInName = AnimationUtils.loadAnimation(this, R.anim.scale_in_name)

        // Start the animations
        logo.startAnimation(scaleInLogo)
        appName.startAnimation(scaleInName)

        val btnContinue: Button = findViewById(R.id.continue_button)
        btnContinue.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}