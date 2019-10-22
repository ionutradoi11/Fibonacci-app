package com.example.fibonacciapp.db.enitity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class App_Entity {


    @PrimaryKey
    var locationId : Int = 0

    @ColumnInfo (name = "LOCATION_NAME")
    var locationName: String = ""
    @ColumnInfo (name = "LOCATION_TITLE")
    var locationTitle: String = ""
    @ColumnInfo (name = "LOCATION_IMAGE")
    var locationPicture: String = ""
}