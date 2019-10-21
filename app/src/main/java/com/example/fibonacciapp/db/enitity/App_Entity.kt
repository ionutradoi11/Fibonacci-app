package com.example.fibonacciapp.db.enitity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class App_Entity {


    @PrimaryKey
    var location_id : Int = 0

    @ColumnInfo (name = "LOCATION_NAME")
    var location_name: String = ""
    @ColumnInfo (name = "LOCATION_TITLE")
    var location_title: String = ""
    @ColumnInfo (name = "LOCATION_IMAGE")
    var location_picture: String = ""
}