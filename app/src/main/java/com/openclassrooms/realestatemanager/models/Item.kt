package com.openclassrooms.realestatemanager.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey



@Entity(foreignKeys = [ForeignKey(entity = Place::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("placeId"))])

data class Item constructor(
        var placeId: Long?

)

{

        @PrimaryKey(autoGenerate = true)
        var id: Long? = null
        var pId: Long? = placeId
        var placeInterests: String? = null

}