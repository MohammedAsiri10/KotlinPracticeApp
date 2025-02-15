package com.example.springtrainingproject2

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val imageSliderButton = getString(R.string.ImageSliderButton)
        val draggableListButton = getString(R.string.DraggableListButton)
        val feature3 = getString(R.string.Feature3)
        val feature4 = getString(R.string.Feature4)
        val feature5 = getString(R.string.Feature5)
        val languageSwitch = findViewById<Switch>(R.id.languageSwitch)

        val buttonList = listOf(
            ButtonItem(imageSliderButton),
            ButtonItem(draggableListButton),
            ButtonItem(feature3),
            ButtonItem(feature4),
            ButtonItem(feature5)
        )


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = ButtonAdapter(buttonList) { feature ->
            val intent = when (feature) {
                draggableListButton -> {
                    Intent(this, DraggableListActivity::class.java)
                }
                imageSliderButton -> {
                    Intent(this, ImageSliderActivity::class.java)
                }
                else -> {
                    Intent(this, FeatureActivity::class.java)
                }
            }
            intent.putExtra("feature_name", feature)
            startActivity(intent)
        }

        // Check current language
        languageSwitch.isChecked = (Locale.getDefault().language == "ar")

        languageSwitch.setOnCheckedChangeListener { _, isChecked ->
            val newLanguage = if (isChecked) "ar" else "en"
            setLocale(newLanguage)
        }
    }

    private fun setLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        // Restart Activity to apply changes
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
