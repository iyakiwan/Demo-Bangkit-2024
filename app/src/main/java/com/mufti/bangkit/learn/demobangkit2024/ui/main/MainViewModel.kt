package com.mufti.bangkit.learn.demobangkit2024.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mufti.bangkit.learn.demobangkit2024.data.Result
import com.mufti.bangkit.learn.demobangkit2024.data.UserRepository
import com.mufti.bangkit.learn.demobangkit2024.model.User
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository) : ViewModel() {
    private val _listUser = MutableLiveData<Result<List<User>>>()
    val listUser: LiveData<Result<List<User>>> = _listUser

    fun getListUser() {
        _listUser.value = Result.Loading
        viewModelScope.launch {
            _listUser.value = repository.getListUser()
        }
    }
}
