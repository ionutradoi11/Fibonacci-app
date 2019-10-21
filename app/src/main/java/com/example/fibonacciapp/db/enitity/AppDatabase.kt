package com.example.fibonacciapp.db.enitity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [(App_Entity::class)], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun appDAO(): App_DAO


}