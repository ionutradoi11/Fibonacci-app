package com.example.fibonacciapp.db.enitity

import androidx.room.*

import com.google.gson.annotations.SerializedName

const val LOCATION_TABLE_ID = 1

@Entity
data class Location (

    var id: Int = 0,
    @ColumnInfo(name = "location_title")
    var title: String? = null,
    @ColumnInfo(name = "location_name")
    var name: String? = null,
    @ColumnInfo(name = "location_image")
    @TypeConverters(StringListConverter::class)
    var pictures: MutableList<String>?
)  {
    @PrimaryKey(autoGenerate = false)
   var idLocation: Int = LOCATION_TABLE_ID
}
