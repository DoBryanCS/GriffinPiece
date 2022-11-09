package com.example.griffinpiece.ui.episode

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.griffinpiece.models.Episode
import com.example.griffinpiece.repositories.EpisodeRepository
import com.example.griffinpiece.repositories.ShowsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpisodeViewModel (private val app: Application, episodeId: Int) : AndroidViewModel(app) {
    var episodeInfo = MutableLiveData<Episode>()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val episodeRepository = EpisodeRepository(getApplication())
            episodeRepository.getEpisode(episodeId, episodeInfo)
        }
    }



}