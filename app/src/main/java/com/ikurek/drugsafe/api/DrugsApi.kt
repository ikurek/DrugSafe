package com.ikurek.drugsafe.api

import com.ikurek.drugsafe.model.DrugModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface DrugsApi {

    @GET("/api/drugs")
    fun getDrug(
        @Header("Authorization") token: String,
        @Query("name") name: String
    )
            : Call<List<DrugModel>>

    @GET("/api/replacements/{id}")
    fun getDrugReplacementsById(
        @Header("Authorization") token: String,
        @Path("id") id: Long
    )
            : Call<List<DrugModel>>

    @GET("/api/packagings")
    fun getDrugByPackageEan(
        @Header("Authorization") token: String,
        @Query("ean") ean: Long
    )
            : Call<DrugModel>
}