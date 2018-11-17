package com.ikurek.drugsafe.database

import androidx.room.*
import com.ikurek.drugsafe.model.DrugModel

@Dao
interface DrugDAO {

    @Query("SELECT * FROM drugs")
    fun getAll(): List<DrugModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg drugModel: DrugModel)

    @Delete
    fun delete(drugModel: DrugModel)
}