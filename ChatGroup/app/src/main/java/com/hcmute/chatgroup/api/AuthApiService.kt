package com.hcmute.chatgroup.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    companion object {
        const val BASE_URL: String = "users"
    }
    @POST("${BASE_URL}/login")
    fun login(@Body body: JsonObject) : Call<JsonObject>
}