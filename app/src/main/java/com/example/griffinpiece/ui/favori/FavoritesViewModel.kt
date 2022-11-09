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
    var favoritesRepository = FavoritesRepository(getApplication())
    var id = 0

    init {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesRepository.getFavorites(shows)
        }
    }

    fun shows() {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesRepository.getFavorites(shows)
        }
    }

    fun delete() {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesRepository.deleteFavorite(id)
        }
    }
}