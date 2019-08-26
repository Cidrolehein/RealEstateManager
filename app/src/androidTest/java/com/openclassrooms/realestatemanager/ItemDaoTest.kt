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
    private val placeId: Long = 0
    private val placeDemo = Place(placeId, "house", "Manhattan", "17.870.000",
            5, "Cum saepe multa, tum memini domi in hemicyclio sedentem, ut solebat, cum et ego essem una et pauci admodum familiares, in eum sermonem illum incidere qui tum forte multis erat in ore. Meministi enim profecto, Attice, et eo magis, quod P. Sulpicio utebare multum, cum is tribunus plebis capitali odio a Q. Pompeio, qui tum erat consul, dissideret, quocum coniunctissime et amantissime vixerat, quanta esset hominum vel admiratio vel querella.",
            "picture", "Address Manhattan", "Free", "06-12-18", "John")
    private val newPlaceDescription = Item(placeId)

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
        this.database?.itemDao()?.updateItem(placeAdded)

        //TEST
        val items = LiveDataTestUtil().getValue(this.database!!.itemDao().getItems(placeId))
        assertTrue(items!!.size == 1)
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