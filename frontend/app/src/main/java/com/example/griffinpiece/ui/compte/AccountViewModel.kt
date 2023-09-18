package com.example.griffinpiece.ui.compte

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.griffinpiece.models.User
import com.example.griffinpiece.repositories.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountViewModel (val app: Application) : AndroidViewModel(app) {
    var accountRepository = AccountRepository(getApplication())
    var userInfo = MutableLiveData<User>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.getUser(userInfo)
        }
    }
}