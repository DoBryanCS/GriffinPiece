package com.example.griffinpiece.ui.Emission

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.griffinpiece.models.Season
import com.example.griffinpiece.models.Show
import com.example.griffinpiece.repositories.ShowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmissionViewModel(private val app: Application): AndroidViewModel(app) {
    var datasetSeasons: MutableLiveData<MutableList<Season>> = MutableLiveData(mutableListOf())
    var show = MutableLiveData<Show>()
    var showId : Int = 0
    var isFavorite = MutableLiveData<Boolean>()



    private val showRepository = ShowRepository(app)

    fun getData(showId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            showRepository.getShowDetails(showId, show)
            showRepository.getSeasons(showId, datasetSeasons)
            showRepository.getFavorite(showId, isFavorite)
        }
    }

    fun addFavorite(showId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            showRepository.addFavorite(showId, isFavorite)
        }
    }
    fun deleteFavorite(showId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            showRepository.deleteFavorite(showId, isFavorite)
        }
    }
}