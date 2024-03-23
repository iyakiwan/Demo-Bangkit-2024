package com.mufti.bangkit.learn.demobangkit2024.data.local.reference

import android.content.Context
import android.content.SharedPreferences
import com.mufti.bangkit.learn.demobangkit2024.model.User
import com.mufti.bangkit.learn.demobangkit2024.utils.PreferenceConstant.PREF_KEY_AVATAR
import com.mufti.bangkit.learn.demobangkit2024.utils.PreferenceConstant.PREF_KEY_EMAIL
import com.mufti.bangkit.learn.demobangkit2024.utils.PreferenceConstant.PREF_KEY_FIRST_NAME
import com.mufti.bangkit.learn.demobangkit2024.utils.PreferenceConstant.PREF_KEY_ID
import com.mufti.bangkit.learn.demobangkit2024.utils.PreferenceConstant.PREF_KEY_LAST_NAME
import com.mufti.bangkit.learn.demobangkit2024.utils.PreferenceConstant.PREF_KEY_LOGIN
import com.mufti.bangkit.learn.demobangkit2024.utils.PreferenceConstant.PREF_NAME

class SharedPreference(
    private val preferences: SharedPreferences
) {
    fun setUser(value: User) {
        val editor = preferences.edit()
        editor.apply {
            putInt(PREF_KEY_ID, value.id)
            putString(PREF_KEY_FIRST_NAME, value.firstName)
            putString(PREF_KEY_LAST_NAME, value.lastName)
            putString(PREF_KEY_EMAIL, value.email)
            putString(PREF_KEY_AVATAR, value.avatar)
            apply()
        }
    }

    fun getUser(): User {
        return User(
            id = preferences.getInt(PREF_KEY_ID, 0),
            firstName = preferences.getString(PREF_KEY_FIRST_NAME, "").toString(),
            lastName = preferences.getString(PREF_KEY_LAST_NAME, "").toString(),
            email = preferences.getString(PREF_KEY_EMAIL, "").toString(),
            avatar = preferences.getString(PREF_KEY_EMAIL, "").toString()
        )
    }

    fun setIsLogin(login: Boolean) {
        val editor = preferences.edit()
        editor.apply {
            putBoolean(PREF_KEY_LOGIN, login)
            apply()
        }
    }

    fun getIsLogin() = preferences.getBoolean(PREF_KEY_LOGIN, false)

    companion object {
        @Volatile
        private var INSTANCE: SharedPreference? = null

        fun getInstance(context: Context): SharedPreference {
            return INSTANCE ?: synchronized(this) {
                val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                val instance = SharedPreference(pref)
                INSTANCE = instance
                instance
            }
        }
    }
}