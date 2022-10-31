package com.example.griffinpiece.repositories

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.griffinpiece.MainActivity.Companion.SRVURL
import com.example.griffinpiece.models.Show
import com.google.gson.Gson

class ShowRepository (private val application: Application) {
    fun getShowDetails(id: MutableLiveData<Int>, title: MutableLiveData<String>, description: MutableLiveData<String>, imageUrl: MutableLiveData<String>,
        releaseDate: MutableLiveData<String>, genre: MutableLiveData<String>, rating: MutableLiveData<Int>) {
        val url = SRVURL + "/show/${id}"
        val queue = Volley.newRequestQueue(application)

        val request = StringRequest(
            Request.Method.GET, url,
            {
                val gson = Gson()
                val details  = gson.fromJson(it, Show::class.java)
                title.value = details.title
                description.value = details.description
                imageUrl.value = details.imageUrl
                releaseDate.value = details.releaseDate
                genre.value = details.genre
                rating.value = details.rating
            },
            {
                Toast.makeText(application, "Erreur dans la requête pour afficher les details du show", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(request)
    }


    fun getSeasons(seasonId: MutableLiveData<Int>, showId: MutableLiveData<Int>, title: MutableLiveData<String>, description: MutableLiveData<String>, imageUrl: MutableLiveData<String>, releaseDate: MutableLiveData<String>) {
        val url = SRVURL + "/seasons/${showId}"
        val queue = Volley.newRequestQueue(application)

        val request = StringRequest(
            Request.Method.GET,url
        )
    }
}