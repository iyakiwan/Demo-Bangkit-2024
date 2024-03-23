package com.mufti.bangkit.learn.demobangkit2024.data.remote.retrofit

import com.mufti.bangkit.learn.demobangkit2024.data.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/users")
    suspend fun getListUsers(@Query("page") page: String): UserResponse
}
