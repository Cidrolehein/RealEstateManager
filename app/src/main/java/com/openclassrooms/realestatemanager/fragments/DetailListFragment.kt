package com.openclassrooms.realestatemanager.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.openclassrooms.realestatemanager.R
import com.openclassrooms.realestatemanager.activities.MainPlaceActivity

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_list, container, false)

        var mainPlaceActivity = MainPlaceActivity()
        var textDescription: TextView = view.findViewById(R.id.place_description)
        textDescription.text = mainPlaceActivity.listOfPlaces?.get(1)!!.description

        return view
    }


}
