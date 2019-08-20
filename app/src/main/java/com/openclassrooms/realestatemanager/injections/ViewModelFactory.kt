package com.openclassrooms.realestatemanager.injections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.openclassrooms.realestatemanager.placelist.ItemViewModel
import com.openclassrooms.realestatemanager.repositories.ItemDataRepository
import com.openclassrooms.realestatemanager.repositories.PlaceDataRepository
import java.util.concurrent.Executor

@Suppress("UNREACHABLE_CODE", "ThrowableNotThrown", "UNCHECKED_CAST")
class ViewModelFactory(private val itemDataSource: ItemDataRepository,
                       private val placeDateSource: PlaceDataRepository,
                       private val executor: Executor) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            ItemViewModel(this.itemDataSource, this.placeDateSource, this.executor) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}