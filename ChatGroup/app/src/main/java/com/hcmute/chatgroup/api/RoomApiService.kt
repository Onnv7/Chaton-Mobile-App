package com.hcmute.chatgroup.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import javax.security.auth.callback.Callback

interface RoomApiService {
    companion object {
        private const val BASE_URL = "rooms"
    }
    @POST("${BASE_URL}/create")
    fun createRoom(@Body body : JsonObject) : Call<JsonObject>
}