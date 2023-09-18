package com.example.griffinpiece.ui.accueil

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.griffinpiece.models.Show
import com.example.griffinpiece.repositories.ShowsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("RedundantVisibilityModifier")
class ShowsViewModel(application: Application) : AndroidViewModel(application) {
    public var shows: MutableLiveData<MutableList<Show>> = MutableLiveData(mutableListOf())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val showRepository = ShowsRepository(getApplication())
            showRepository.getShows(shows)
        }
    }
}