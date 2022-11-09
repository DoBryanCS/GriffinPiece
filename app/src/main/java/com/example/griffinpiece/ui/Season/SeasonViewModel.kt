package com.example.griffinpiece.ui.Season

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.griffinpiece.models.Episode
import com.example.griffinpiece.models.Season
import com.example.griffinpiece.repositories.SeasonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SeasonViewModel (private val app: Application): AndroidViewModel(app) {
    var detailsSeason = MutableLiveData<Season>()

    private val seasonRepository =  SeasonRepository(app)

    fun getData(seasonId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            seasonRepository.getSeason(seasonId, detailsSeason)
        }
    }
}