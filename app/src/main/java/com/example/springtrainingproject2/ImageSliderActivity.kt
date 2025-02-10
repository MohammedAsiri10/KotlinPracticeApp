package com.example.springtrainingproject2

import android.os.Bundle
import android.os.Handler
import android.widget.LinearLayout
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable

class ImageSliderActivity : AppCompatActivity() {

    // Declare class-level variables
    private lateinit var viewPager: ViewPager2
    private lateinit var indicatorLayout: LinearLayout
    private lateinit var autoSlideSwitch: Switch
    private var currentPage = 0  // Track the current page
    private lateinit var handler: Handler

    // List of image resources (replace with your actual image resource names in the drawable folder)
    private val images = listOf(
        R.drawable.a,
        R.drawable.b,
        R.drawable.c,
        R.drawable.d,
        R.drawable.e,
        R.drawable.f,
        R.drawable.g,
        R.drawable.h,
        R.drawable.i,
        R.drawable.j
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_slider)

        // Initialize the views
        viewPager = findViewById(R.id.viewPager)
        indicatorLayout = findViewById(R.id.indicatorLayout)
        autoSlideSwitch = findViewById(R.id.switchAutoSlide)
        handler = Handler()

        // Set up ViewPager adapter
        viewPager.adapter = ImageSliderAdapter(images)

        // Set up indicators for the images
        setUpIndicators(images.size, indicatorLayout)

        // Register page change callback to update indicators
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPage = position
                updateIndicators(position, images.size, indicatorLayout)
            }
        })

        // Handle auto-slide toggle
        autoSlideSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                startAutoSlide()  // Start auto
            } else {
                stopAutoSlide()  // Stop auto
            }
        }
    }

    // Function to start auto-slide
    private fun startAutoSlide() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (currentPage == images.size - 1) {
                    currentPage = 0  // Loop back to the first image
                } else {
                    currentPage++
                }
                viewPager.setCurrentItem(currentPage, true)
                handler.postDelayed(this, 3000)  // Auto slide every 3 seconds
            }
        }, 3000)
    }

    // Function to stop auto-slide
    private fun stopAutoSlide() {
        handler.removeCallbacksAndMessages(null)  // Remove all callbacks to stop auto sliding
    }

    // Function to set up indicators
    private fun setUpIndicators(count: Int, indicatorLayout: LinearLayout) {
        // Remove previous indicators
        indicatorLayout.removeAllViews()

        // Add new dots (indicators) based on image count
        for (i in 0 until count) {
            val dot = createDot()
            indicatorLayout.addView(dot)
        }
    }

    // Function to create a dot (indicator)
    private fun createDot(): LinearLayout {
        val dot = LinearLayout(this)
        val params = LinearLayout.LayoutParams(16.dp, 16.dp)  // Set the size of the dot
        params.marginStart = 8.dp  // Add some margin between dots
        params.marginEnd = 8.dp
        dot.layoutParams = params

        // Create a circle shape for the dot
        val shape = MaterialShapeDrawable()
        shape.shapeAppearanceModel = shape.shapeAppearanceModel.toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, 50f)  // Rounded corners to make it a circle
            .build()

        // Set the background color of the dot (inactive color)
        dot.background = shape
        dot.setBackgroundColor(ContextCompat.getColor(this, R.color.inactiveIndicator))
        return dot
    }

    // Function to update the color of indicators based on the current page
    private fun updateIndicators(position: Int, count: Int, indicatorLayout: LinearLayout) {
        for (i in 0 until count) {
            val dot = indicatorLayout.getChildAt(i) as LinearLayout
            if (i == position) {
                // Set active color for the current page
                dot.setBackgroundColor(ContextCompat.getColor(this, R.color.activeIndicator))
            } else {
                // Set inactive color for other dots
                dot.setBackgroundColor(ContextCompat.getColor(this, R.color.inactiveIndicator))
            }
        }
    }

    // Extension function to convert dp to pixels
    private val Int.dp: Int
        get() = (this * resources.displayMetrics.density).toInt()
}