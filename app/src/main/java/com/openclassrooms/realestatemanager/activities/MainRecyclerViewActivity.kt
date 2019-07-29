package com.openclassrooms.realestatemanager.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.openclassrooms.realestatemanager.R

/**
 * Main Kotlin activity to implement RecyclerView
 */
class MainRecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_recycler_view)
    }
}
