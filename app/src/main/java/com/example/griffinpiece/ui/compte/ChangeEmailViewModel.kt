package com.example.griffinpiece.ui.compte

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.griffinpiece.models.User
import com.example.griffinpiece.repositories.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChangeEmailViewModel(val app: Application, newEmail: String) : AndroidViewModel(app) {
    private val accountRepository = AccountRepository(getApplication())
    val success = MutableLiveData<Boolean>()


    fun changeEmail(newEmail: String, currentPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.changeEmail(newEmail, currentPassword, success)

        }
    }
}