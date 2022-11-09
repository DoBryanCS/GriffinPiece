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


    private val signUpRepository = SignUpRepository(app)
    var success = MutableLiveData<Boolean>()

    fun signUp(email: String, username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            signUpRepository.signUp(email,username, password, success)

        }
    }


}