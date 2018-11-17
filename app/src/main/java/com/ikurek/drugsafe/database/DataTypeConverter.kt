package com.ikurek.drugsafe.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringListToString(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToStringList(string: String): List<String> {
        return gson.fromJson(string)
    }


    private inline fun <reified T> Gson.fromJson(json: String) =
        this.fromJson<T>(json, object : TypeToken<T>() {}.type)!!
}