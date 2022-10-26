package com.example.griffinpiece.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.griffinpiece.repositories.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel (val app: Application) : AndroidViewModel(app){
    val loginRepository = LoginRepository(app)
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.login(email.value, password.value)
        }
    }
}