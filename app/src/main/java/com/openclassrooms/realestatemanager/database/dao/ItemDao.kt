package com.openclassrooms.realestatemanager.database.dao

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.openclassrooms.realestatemanager.models.Item

interface ItemDao {

    @Query("SELECT * FROM Item WHERE placeId = :placeId")
    fun getItems(placeId: Long): List<Item>

    @Insert
    fun insertItem(item: Item)

    @Update
    fun updateItem(item: Item)

    @Query("DELETE from item WHERE id = :itemId")
    fun deleteItem(itemId: Long)
}