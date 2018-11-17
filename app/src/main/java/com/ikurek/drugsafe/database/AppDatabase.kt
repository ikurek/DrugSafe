package com.ikurek.drugsafe.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ikurek.drugsafe.model.DrugModel

@Database(entities = [DrugModel::class], version = 3)
@TypeConverters(DataTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun drugDAO(): DrugDAO
}