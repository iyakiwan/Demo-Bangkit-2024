package com.mufti.bangkit.learn.demobangkit2024.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mufti.bangkit.learn.demobangkit2024.data.local.datastore.SettingPreferences
import com.mufti.bangkit.learn.demobangkit2024.data.local.reference.SharedPreference
import com.mufti.bangkit.learn.demobangkit2024.data.remote.mapper.UserMapper
import com.mufti.bangkit.learn.demobangkit2024.data.remote.retrofit.ApiService
import com.mufti.bangkit.learn.demobangkit2024.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository private constructor(
    private val apiService: ApiService,
    private val preference: SharedPreference,
    private val dataStore: SettingPreferences,
) {

    fun getListUser(): LiveData<Result<List<User>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getListUsers("1")
            val dataResult = UserMapper.mapListUserResponseToListUser(response)

            emit(Result.Success(dataResult))
        } catch (e: Exception) {
            Log.d("UserRepository", "getListUser: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getLocalUser() = preference.getUser()

    fun setLocalUser(user: User) = preference.setUser(user)

    fun getIsLogin() = preference.getIsLogin()

    fun setIsLogin(login: Boolean) = preference.setIsLogin(login)

    fun getThemeSetting(): Flow<Boolean> = dataStore.getThemeSetting()

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) =
        dataStore.saveThemeSetting(isDarkModeActive)

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            preference: SharedPreference,
            dataStore: SettingPreferences
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, preference, dataStore)
            }.also { instance = it }
    }
}
