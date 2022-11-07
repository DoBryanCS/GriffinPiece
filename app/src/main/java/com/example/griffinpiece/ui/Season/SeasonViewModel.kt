package com.example.griffinpiece.ui.Season

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.griffinpiece.models.Episode
import com.example.griffinpiece.models.Season
import com.example.griffinpiece.repositories.SeasonRepository

class SeasonViewModel (private val app: Application): AndroidViewModel(app) {
    public var detailsSeason = MutableLiveData<Season>()
    public var seasonId = MutableLiveData<Int>()
    public var showId= MutableLiveData<Int>()
    public var title = MutableLiveData<String>()
    public var description = MutableLiveData<String>()
    public var imageUrl = MutableLiveData<String>()
    public var releaseDate =  MutableLiveData<String>()
    public var datasetEpisode = MutableLiveData<MutableList<Episode>>()

    private val seasonRepository =  SeasonRepository(app)

    init {
        seasonRepository.getSeason(detailsSeason,seasonId, showId, title, description, imageUrl, releaseDate)
        seasonRepository.getEpisodes(seasonId, datasetEpisode)
    }
}