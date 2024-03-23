package com.mufti.bangkit.learn.demobangkit2024.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.mufti.bangkit.learn.demobangkit2024.data.UserRepository
import com.mufti.bangkit.learn.demobangkit2024.data.local.datastore.SettingPreferences
import com.mufti.bangkit.learn.demobangkit2024.data.local.reference.SharedPreference
import com.mufti.bangkit.learn.demobangkit2024.data.remote.retrofit.ApiConfig


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService(context)
        val preference = SharedPreference.getInstance(context)
        val dataStore = SettingPreferences.getInstance(context.dataStore)
        return UserRepository.getInstance(
            apiService = apiService,
            preference = preference,
            dataStore = dataStore,
        )
    }
}
