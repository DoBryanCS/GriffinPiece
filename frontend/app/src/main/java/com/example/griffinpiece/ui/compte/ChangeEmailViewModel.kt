package com.example.griffinpiece.ui.compte

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.griffinpiece.models.User
import com.example.griffinpiece.repositories.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChangeEmailViewModel(val app: Application) : AndroidViewModel(app) {
    private val accountRepository = AccountRepository(getApplication())
    val success = MutableLiveData<Boolean>()


    fun changeEmail(currentPassword: String, newEmail: String) {
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.changeEmail(currentPassword, newEmail, success)

        }
    }
}