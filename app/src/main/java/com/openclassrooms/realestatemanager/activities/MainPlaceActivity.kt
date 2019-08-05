package com.openclassrooms.realestatemanager.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.openclassrooms.realestatemanager.R
import com.openclassrooms.realestatemanager.adapters.RecyclerAdapter
import com.openclassrooms.realestatemanager.fragments.MainPlaceListFragment
import kotlinx.android.synthetic.main.activity_main_recycler_view.*

/**
 * Main Kotlin activity to implement RecyclerView
 */
class MainPlaceActivity : AppCompatActivity() {

    private val fragment = MainPlaceListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_recycler_view)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorTitle))
        setSupportActionBar(toolbar)

        supportFragmentManager.inTransaction {
            replace(R.id.fragment_container, fragment)
        }

    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

}
