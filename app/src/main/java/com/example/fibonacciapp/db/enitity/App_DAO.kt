package com.example.fibonacciapp.db.enitity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface App_DAO {

    @Insert
    fun saveLocation(location: Location)

    @Query("select * from Location")
    fun getLocation() : List<Location>

}