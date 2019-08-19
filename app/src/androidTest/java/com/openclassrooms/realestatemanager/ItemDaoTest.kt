package com.openclassrooms.realestatemanager

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.openclassrooms.realestatemanager.database.SaveMyPlaceDatabase
import com.openclassrooms.realestatemanager.models.Item
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
    private val newPlaceDescription = Item("Lorem ipsum dolor sit amet, " +
            "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et " +
            "dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco " +
            "laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
            "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla " +
            "pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui " +
            "officia deserunt mollit anim id est laborum.", placeId)

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
    fun closeDp() {
        database?.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun getItemsWhenNoItemInserted() {
        // TEST
        val items = LiveDataTestUtil().getValue(this.database!!.itemDao().getItems(placeId))
        assertTrue(items!!.isEmpty())
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

    @Test
    @Throws(InterruptedException::class)
    fun insertAndUpdateItem() {
        // BEFORE : Adding demo user & demo items. Next, update item added & re-save it
        this.database?.placeDao()?.createPlace(placeDemo)
        this.database?.itemDao()?.insertItem(newPlaceDescription)
        val placeAdded = LiveDataTestUtil().getValue(this.database!!.itemDao().getItems(placeId))!![0]
        placeAdded.isSelected = true
        this.database?.itemDao()?.updateItem(placeAdded)

        //TEST
        val items = LiveDataTestUtil().getValue(this.database!!.itemDao().getItems(placeId))
        assertTrue(items!!.size == 1 && items[0].isSelected)
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertAndDeleteItem() {
        // BEFORE : Adding demo user & demo item. Next, get the item added & delete it.
        this.database?.placeDao()?.createPlace(placeDemo)
        this.database?.itemDao()?.insertItem(newPlaceDescription)
        val itemAdded = LiveDataTestUtil().getValue(this.database!!.itemDao().getItems(placeId))!![0]
        this.database?.itemDao()?.deleteItem(itemAdded.id!!)

        //TEST
        val items = LiveDataTestUtil().getValue(this.database!!.itemDao().getItems(placeId))
        assertTrue(items!!.isEmpty())
    }

}