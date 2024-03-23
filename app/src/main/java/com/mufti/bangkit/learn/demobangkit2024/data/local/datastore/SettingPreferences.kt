package com.mufti.bangkit.learn.demobangkit2024.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    private val themeKey = booleanPreferencesKey("theme_setting")


    fun getThemeSetting(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[themeKey] ?: false
    }


    suspend fun saveThemeSetting(isDarkModeActive: Boolean) =
        dataStore.edit { preferences ->
            preferences[themeKey] = isDarkModeActive
        }


    companion object {
        @Volatile
        private var INSTANCE: SettingPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): SettingPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}
