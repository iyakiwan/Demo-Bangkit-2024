package com.mufti.bangkit.learn.demobangkit2024.ui.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FormViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }
}