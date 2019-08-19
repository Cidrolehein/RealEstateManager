package com.openclassrooms.realestatemanager

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.openclassrooms.realestatemanager.database.SaveMyPlaceDatabase
import com.openclassrooms.realestatemanager.models.Place
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ItemDaoTest {

    // FOR DATA
    private var database: SaveMyPlaceDatabase? = null
    // DATA SET FOR TEST
    private val placeId: Long = 1
    private val placeDemo = Place(placeId, "Home", 100000)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun initDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        this.database = Room.inMemoryDatabaseBuilder(context,
                SaveMyPlaceDatabase::class.java)
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDp(){
        database?.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertAndGetPlace() {
        // BEFORE : Adding a new user
        this.database?.placeDao()?.createPlace(placeDemo)
        // TEST
        val place = LiveDataTestUtil().getValue(this.database!!.placeDao().getPlace(placeId))
        assertTrue(place?.placeTitle.equals(placeDemo.placeTitle) && place!!.id == placeId)
    }

}