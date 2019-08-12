package com.openclassrooms.realestatemanager.activities

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.realestatemanager.R
import com.openclassrooms.realestatemanager.adapters.RecyclerAdapter
import com.openclassrooms.realestatemanager.fragments.DetailListFragment

/**
 * Main Kotlin activity to implement RecyclerView
 */
class MainPlaceActivity : AppCompatActivity(), RecyclerAdapter.ItemClickListener {

    private val fragmentDetailList = DetailListFragment()
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_recycler_view)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorTitle))
        setSupportActionBar(toolbar)

        // RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(this)
        adapter = RecyclerAdapter(this)

        // Configure screen
        detectSizeScreen()
        screenSizeConditions()

    }

    override fun onItemClick(position: Int) {
        replaceFragment(fragmentDetailList, R.id.fragment_details_container)
    }

    /**
     * begin transaction of a fragment
     */
    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    /**
     * add fragment
     */
    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }

    /**
     * replace fragment
     */
    private fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { replace(frameId, fragment) }
    }

    /**
     * Detect the size of screen
     */
    private fun detectSizeScreen() {
        //Determine screen size
        when {
            resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_LARGE -> Toast.makeText(this, "Large screen", Toast.LENGTH_LONG).show()
            resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_XLARGE -> Toast.makeText(this, "X Large screen", Toast.LENGTH_LONG).show()
            resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_NORMAL -> Toast.makeText(this, "Normal sized screen", Toast.LENGTH_LONG).show()
            resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_SMALL -> Toast.makeText(this, "Small sized screen", Toast.LENGTH_LONG).show()
            else -> Toast.makeText(this, "Screen size is neither large, normal or small", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Conditions of different screen size
     */
    private fun screenSizeConditions() {
        when {
            resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_XLARGE -> addFragment(fragmentDetailList, R.id.fragment_details_container)
            resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_LARGE -> addFragment(fragmentDetailList, R.id.fragment_details_container)
        }
    }

}
