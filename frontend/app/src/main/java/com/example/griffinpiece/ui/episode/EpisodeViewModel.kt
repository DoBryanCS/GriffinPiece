package com.example.griffinpiece.ui.episode

import android.app.Application
import androidx.lifecycle.*
import com.example.griffinpiece.models.Episode
import com.example.griffinpiece.repositories.EpisodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EpisodeViewModelFactory(
    val application: Application,
    private val episodeId: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EpisodeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EpisodeViewModel(this.application,
                episodeId) as T
        }
        else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

class EpisodeViewModel (private val app: Application, episodeId: Int) : AndroidViewModel(app) {
    var episodeInfo = MutableLiveData<Episode>()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val episodeRepository = EpisodeRepository(getApplication())
            episodeRepository.getEpisode(episodeId, episodeInfo)
        }
    }



}