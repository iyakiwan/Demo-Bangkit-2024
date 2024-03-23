package com.mufti.bangkit.learn.demobangkit2024.di

import android.content.Context
import com.mufti.bangkit.learn.demobangkit2024.data.UserRepository
import com.mufti.bangkit.learn.demobangkit2024.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService(context)
        return UserRepository.getInstance(apiService)
    }
}
