package com.mufti.bangkit.learn.demobangkit2024.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mufti.bangkit.learn.demobangkit2024.data.Result
import com.mufti.bangkit.learn.demobangkit2024.data.UserRepository
import com.mufti.bangkit.learn.demobangkit2024.model.User

class MainViewModel(private val repository: UserRepository) : ViewModel() {
    val listUser: LiveData<Result<List<User>>> = repository.getListUser()

    fun setLocalUser(user: User) = repository.setLocalUser(user)

    fun getLocalUser() = repository.getLocalUser()

    fun getIsLogin() = repository.getIsLogin()

    fun setIsLogin(login: Boolean) = repository.setIsLogin(login)

    fun getThemeSettings(): LiveData<Boolean> = repository.getThemeSetting().asLiveData()
}
