package com.ikurek.drugsafe.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scheduled_drugs")
data class ScheduledDrugModel(
    @PrimaryKey
    val scheduled_drug_id: Long?,
    @Embedded
    var drug: DrugModel,
    val days: List<Int>,
    val hour: Int,
    val minutes: Int,
    val notify: Boolean

)