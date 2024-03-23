package com.mufti.bangkit.learn.demobangkit2024.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mufti.bangkit.learn.demobangkit2024.data.Result
import com.mufti.bangkit.learn.demobangkit2024.data.UserRepository
import com.mufti.bangkit.learn.demobangkit2024.model.User

class MainViewModel(repository: UserRepository) : ViewModel() {
    val listUser: LiveData<Result<List<User>>> = repository.getListUser()
}
