package com.ikurek.drugsafe.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ikurek.drugsafe.model.ScheduledDrugModel

@Database(entities = arrayOf(ScheduledDrugModel::class), version = 1)
@TypeConverters(DataTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scheduledDrugDAO(): ScheduledDrugDAO
}