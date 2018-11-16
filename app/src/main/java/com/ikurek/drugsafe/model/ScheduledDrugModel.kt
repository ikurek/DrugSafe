package com.ikurek.drugsafe.model

import androidx.room.*
import com.ikurek.drugsafe.database.DataTypeConverter

@Entity(tableName = "scheduled_drugs")
data class ScheduledDrugModel(
    @PrimaryKey
    val scheduled_drug_id: Long,
    @TypeConverters(DataTypeConverter::class)
    @Embedded
    var drug: DrugModel,
    @ColumnInfo(name = "days")
    val days: String,
    @ColumnInfo(name = "hours")
    val hours: String
)