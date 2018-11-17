package com.ikurek.drugsafe.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ikurek.drugsafe.model.DrugModel
import com.ikurek.drugsafe.model.ScheduledDrugModel

@Database(entities = arrayOf(ScheduledDrugModel::class, DrugModel::class), version = 2)
@TypeConverters(DataTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scheduledDrugDAO(): ScheduledDrugDAO
    abstract fun drugDAO(): DrugDAO
}