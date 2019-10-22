package com.example.fibonacciapp.db.enitity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database (entities = [(Location::class)], version = 1)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun appDAO(): App_DAO


}