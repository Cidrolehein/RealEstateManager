package com.openclassrooms.realestatemanager.repositories

import com.openclassrooms.realestatemanager.database.dao.ItemDao
import com.openclassrooms.realestatemanager.models.Item
import androidx.lifecycle.LiveData


class ItemDataRepository(private val itemDao: ItemDao) {

    // --- GET ---

    fun getItems(userId: Long): LiveData<List<Item>> {
        return itemDao.getItems(userId)
    }

    // --- CREATE ---
    fun createItem(item: Item) {
        itemDao.insertItem(item)
    }

    // --- DELETE ---
    fun deleteItem(itemId: Long) {
        itemDao.deleteItem(itemId)
    }

    // --- UPDATE ---
    fun updateItem(item: Item) {
        itemDao.updateItem(item)
    }

}