package com.openclassrooms.realestatemanager.placelist

import androidx.lifecycle.ViewModel
import com.openclassrooms.realestatemanager.repositories.ItemDataRepository
import com.openclassrooms.realestatemanager.repositories.PlaceDataRepository
import java.util.concurrent.Executor

class ItemViewModel constructor(itemDataSource: ItemDataRepository,
                                placeDataSource: PlaceDataRepository,
                                executor: Executor): ViewModel() {

    init {

        fun init(userId:Long){
            if (this.currentPlace != null){
                return
            }
            currentPlace = placeDataSource.
        }

    }
}