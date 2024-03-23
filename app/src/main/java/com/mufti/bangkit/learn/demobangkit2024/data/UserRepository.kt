package com.mufti.bangkit.learn.demobangkit2024.data

import android.util.Log
import com.mufti.bangkit.learn.demobangkit2024.data.remote.mapper.UserMapper
import com.mufti.bangkit.learn.demobangkit2024.data.remote.retrofit.ApiService
import com.mufti.bangkit.learn.demobangkit2024.model.User

class UserRepository private constructor(
    private val apiService: ApiService,
) {

    suspend fun getListUser(): Result<List<User>> {
        return try {
            val response = apiService.getListUsers("1")
            val dataResult = UserMapper.mapListUserResponseToListUser(response)

            Result.Success(dataResult)
        } catch (e: Exception) {
            Log.d("UserRepository", "getListUser: ${e.message.toString()} ")
            Result.Error(e.message.toString())
        }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService)
            }.also { instance = it }
    }
}
