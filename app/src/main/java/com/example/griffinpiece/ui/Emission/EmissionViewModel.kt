package com.example.griffinpiece.ui.Emission

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.griffinpiece.repositories.ShowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmissionViewModel(private val app: Application): AndroidViewModel(app) {
    var id = MutableLiveData<Int>()
    var title = MutableLiveData<String>()
    var description = MutableLiveData<String>()
    var imageUrl = MutableLiveData<String>()
    var genre = MutableLiveData<String>()
    var rating = MutableLiveData<Int>()



    val showRepository = ShowRepository(app)

    init {
        viewModelScope.launch(Dispatchers.IO){
            showRepository.getShowDetails(id, title, description, imageUrl, genre, rating)
        }
    }
}