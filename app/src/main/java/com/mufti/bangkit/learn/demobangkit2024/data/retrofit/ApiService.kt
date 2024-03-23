package com.mufti.bangkit.learn.demobangkit2024.data.retrofit

import com.mufti.bangkit.learn.demobangkit2024.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/users")
    fun getListUsers(@Query("page") page: String): Call<UserResponse>
}
