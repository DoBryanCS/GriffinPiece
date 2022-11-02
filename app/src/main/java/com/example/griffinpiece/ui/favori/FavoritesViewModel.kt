package com.example.griffinpiece.ui.favori

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.griffinpiece.models.Show
import com.example.griffinpiece.repositories.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("RedundantVisibilityModifier")
class FavoritesViewModel(val app: Application) : AndroidViewModel(app) {
    public var shows: MutableLiveData<MutableList<Show>> = MutableLiveData(mutableListOf())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val favoritesRepository = FavoritesRepository(getApplication())
            favoritesRepository.getFavorites(shows)
        }
    }
}