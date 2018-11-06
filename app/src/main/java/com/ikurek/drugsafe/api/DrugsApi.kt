package com.ikurek.drugsafe.api

import com.ikurek.drugsafe.model.DrugModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface DrugsApi {

    // FIXME: API requires get method as parameter?
    @GET("/api/v1/drugs")
    fun getDrug(@Header("Authorization") token: String, @Query("name") name: String): Call<List<DrugModel>>

    @GET("/api/v1/drugs/{id}")
    fun getDrugById(@Path("id") id: Long): Call<DrugModel>
}