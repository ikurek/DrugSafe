package com.ikurek.drugsafe.api

import com.google.gson.JsonObject
import com.ikurek.drugsafe.model.LoginModel
import com.ikurek.drugsafe.model.RegisterModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("/login")
    fun login(@Body jsonObject: JsonObject): Call<LoginModel>

    @POST("/register")
    fun register(@Body jsonObject: JsonObject): Call<RegisterModel>
}