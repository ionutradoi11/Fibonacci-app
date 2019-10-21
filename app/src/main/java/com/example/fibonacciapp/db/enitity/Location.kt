package com.example.fibonacciapp.db.enitity

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName

const val LOCATION_TABLE_ID = 0



@Entity(tableName = "location_table")
data class Location (

    val id: Int = 0,

    val title: String? = null,

    val name: String? = null,
    @SerializedName("pictures")
    val pictures: List<String>? = null
) {
    @PrimaryKey(autoGenerate = false)
    var idLocation: Int = LOCATION_TABLE_ID
}
