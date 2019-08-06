package com.openclassrooms.realestatemanager.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    }

    override fun onItemClick(position: Int){
        replaceFragment(fragmentDetailList, R.id.fragment_details_container)
    }

    /**
     * begin transaction of a fragment
     */
    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    /**
     * add fragment
     */
    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }

    /**
     * replace fragment
     */
    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction{replace(frameId, fragment)}
    }

}
