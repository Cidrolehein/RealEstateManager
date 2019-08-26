package com.openclassrooms.realestatemanager.todolist

import androidx.annotation.Nullable
import androidx.lifecycle.ViewModel
import com.openclassrooms.realestatemanager.repositories.ItemDataRepository
import com.openclassrooms.realestatemanager.repositories.PlaceDataRepository
import java.util.concurrent.Executor
import androidx.lifecycle.LiveData
import com.openclassrooms.realestatemanager.models.Place
import com.openclassrooms.realestatemanager.models.Item


class ItemViewModel constructor(private val itemDataSource: ItemDataRepository,
                                private val placeDataSource: PlaceDataRepository,
                                private val executor: Executor) : ViewModel() {

    // DATA
    @Nullable
    lateinit var currentPlace: LiveData<Place>

    fun init(placeId: Long) {
        currentPlace = placeDataSource.getUser(placeId)
    }

    // -------------
    // FOR USER
    // -------------

    fun getPlace(placeId: Long): LiveData<Place> {
        return currentPlace
    }

    // -------------
    // FOR ITEM
    // -------------

    fun getItems(placeId: Long): LiveData<List<Item>> {
        return itemDataSource.getItems(placeId)
    }

    fun createItem(item: Item) {
        executor.execute { itemDataSource.createItem(item) }
    }

    fun deleteItem(itemId: Long) {
        executor.execute { itemDataSource.deleteItem(itemId) }
    }

    fun updateItem(item: Item) {
        executor.execute { itemDataSource.updateItem(item) }
    }

}