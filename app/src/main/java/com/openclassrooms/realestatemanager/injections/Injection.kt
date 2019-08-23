package com.openclassrooms.realestatemanager.injections

import android.content.Context
import com.openclassrooms.realestatemanager.database.SaveMyPlaceDatabase
import com.openclassrooms.realestatemanager.repositories.ItemDataRepository
import com.openclassrooms.realestatemanager.repositories.PlaceDataRepository
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class Injection {

    fun provideItemDataSource(context: Context): ItemDataRepository {
        val database = SaveMyPlaceDatabase.DatabaseProvider.getInstance(context)
        return ItemDataRepository(database!!.itemDao())
    }

    fun provideUserDataSource(context: Context): PlaceDataRepository {
        val database = SaveMyPlaceDatabase.DatabaseProvider.getInstance(context)
        return PlaceDataRepository(database!!.placeDao())
    }

    fun provideExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSourceItem = provideItemDataSource(context)
        val dataSourceUser = provideUserDataSource(context)
        val executor = provideExecutor()
        return ViewModelFactory(dataSourceItem, dataSourceUser, executor)
    }
}