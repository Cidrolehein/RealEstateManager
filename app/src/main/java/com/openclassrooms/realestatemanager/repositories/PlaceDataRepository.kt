package com.openclassrooms.realestatemanager.repositories

import com.openclassrooms.realestatemanager.database.dao.PlaceDao
import androidx.lifecycle.LiveData
import com.openclassrooms.realestatemanager.models.Place


class PlaceDataRepository constructor(private val placeDao: PlaceDao) {

    // --- GET USER ---
    fun getUser(userId: Long): LiveData<Place> {
        return placeDao.getPlace(userId)
    }

}