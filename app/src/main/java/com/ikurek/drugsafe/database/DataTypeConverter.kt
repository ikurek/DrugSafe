package com.ikurek.drugsafe.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ikurek.drugsafe.model.DrugModel

class DataTypeConverter {

    val gson = Gson()

    @TypeConverter
    fun drugModelToString(drugModel: DrugModel): String {
        return gson.toJson(drugModel)
    }

    @TypeConverter
    fun stringToDrugModel(drug: String): DrugModel {
        return gson.fromJson(drug, DrugModel::class.java)
    }

    @TypeConverter
    fun stringListToString(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToStringList(string: String): List<String> {
        return gson.fromJson(string)
    }

    @TypeConverter
    fun intListToString(list: List<Int>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToIntList(string: String): List<Int> {
        return gson.fromJson(string)
    }


    inline fun <reified T> Gson.fromJson(json: String) =
        this.fromJson<T>(json, object : TypeToken<T>() {}.type)
}