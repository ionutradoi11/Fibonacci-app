package com.example.fibonacciapp.db.enitity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface App_DAO {

    @Insert
    fun saveLocation(location: App_Entity)

    @Query("select * from App_Entity")
    fun readLocation() : List<App_Entity>

}