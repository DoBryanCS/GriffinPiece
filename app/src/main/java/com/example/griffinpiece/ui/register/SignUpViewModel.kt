package com.example.griffinpiece.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.griffinpiece.repositories.LoginRepository
import com.example.griffinpiece.repositories.SignUpRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel (private val app: Application) : AndroidViewModel(app) {


    val signUpRepository = SignUpRepository(app)

    val email = MutableLiveData<String>()
    val username = MutableLiveData<String>()
    val password =MutableLiveData<String>()

    fun signUp() {
        viewModelScope.launch(Dispatchers.IO) {
            signUpRepository.signUp(email.value,username.value, password.value)

        }
    }


}