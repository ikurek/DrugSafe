package com.ikurek.drugsafe.api

import retrofit2.Call
import retrofit2.http.GET

interface DrugsApi {

    @GET("/api/v1/drugs")
    fun getDrug(): Call<Void>
}