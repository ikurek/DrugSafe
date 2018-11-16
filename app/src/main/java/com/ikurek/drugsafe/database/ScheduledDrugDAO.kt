package com.ikurek.drugsafe.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ikurek.drugsafe.model.ScheduledDrugModel

@Dao
interface ScheduledDrugDAO {

    @Query("SELECT * FROM scheduled_drugs")
    fun getAll(): List<ScheduledDrugModel>

    @Insert
    fun save(vararg scheduledDrugModel: ScheduledDrugModel)

    @Delete
    fun delete(scheduledDrugModel: ScheduledDrugModel)

}