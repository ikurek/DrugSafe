package com.ikurek.drugsafe.api

import com.ikurek.drugsafe.model.LoginModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UsersApi {

    @POST("/api/v1/login")
    @Headers("Content-Type: application/json", "Accept: application/json ")
    fun login(@Body loginModel: LoginModel): Call<Void>

    @POST("/api/v1/register")
    @Headers("Content-Type: application/json", "Accept: application/json")
    fun register(@Body loginModel: LoginModel): Call<Void>
}