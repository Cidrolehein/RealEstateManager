package com.openclassrooms.realestatemanager.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.openclassrooms.realestatemanager.models.Place

interface PlaceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createPlace(place: Place)

    @Query("SELECT * FROM Place WHERE id = :userId")
    fun getPlace(userId: Long): LiveData<Place>
}