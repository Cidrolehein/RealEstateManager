package com.openclassrooms.realestatemanager.repositories

import com.openclassrooms.realestatemanager.database.dao.ItemDao
import com.openclassrooms.realestatemanager.models.Item

class ItemDataRepository (itemDao: ItemDao) {

    init {
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

}