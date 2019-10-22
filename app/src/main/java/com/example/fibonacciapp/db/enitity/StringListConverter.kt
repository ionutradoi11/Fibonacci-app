package com.example.fibonacciapp.db.enitity

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



class StringListConverter {
    @TypeConverter
    fun restoreList(listOfString: String): MutableList<String> {
        return Gson().fromJson(listOfString, object : TypeToken<List<String>>() {
        }.type)
    }

    @TypeConverter
    fun saveList(listOfString: MutableList<String>): String {
        return Gson().toJson(listOfString)
    }
}