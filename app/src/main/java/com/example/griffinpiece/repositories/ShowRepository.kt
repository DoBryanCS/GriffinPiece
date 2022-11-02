package com.example.griffinpiece.repositories

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.griffinpiece.MainActivity.Companion.SRVURL
import com.example.griffinpiece.models.Season
import com.example.griffinpiece.models.Show
import com.google.gson.Gson

class ShowRepository (private val application: Application) {
    fun getShowDetails(
        id: MutableLiveData<Int>, details: MutableLiveData<Show>, title: MutableLiveData<String>, description: MutableLiveData<String>, imageUrl: MutableLiveData<String>,
        releaseDate: MutableLiveData<String>, genre: MutableLiveData<String>, rating: MutableLiveData<Int>) {
        val url = SRVURL + "/show/${id.value}"
        val queue = Volley.newRequestQueue(application)

        val request = StringRequest(
            Request.Method.GET, url,
            {
                val gson = Gson()
                details.value  = gson.fromJson(it, Show::class.java)
                title.value = details.value?.title
                description.value = details.value?.description
                imageUrl.value = details.value?.imageUrl
                releaseDate.value = details.value?.releaseDate
                genre.value = details.value?.genre
                rating.value = details.value?.rating
            },
            {
                Toast.makeText(application, "Erreur dans la requête pour afficher les details du show", Toast.LENGTH_SHORT).show()
                Log.e("Erreur", it.toString())
            }
        )
        queue.add(request)
    }


    fun getSeasons(showId: MutableLiveData<Int> ,datasetSeasons: MutableLiveData<MutableList<Season>>) {
        val url = SRVURL + "/seasons/${showId.value}"
        val queue = Volley.newRequestQueue(application)

        val request = StringRequest(
            Request.Method.GET,url,
            Response.Listener {
                val gson = Gson()
                val seasonsArray = gson.fromJson(it, Array<Season>::class.java)
                datasetSeasons.value =seasonsArray.toMutableList()
            },
            Response.ErrorListener {
                Log.e("Erreur", it.toString())
            }
        )
        queue.add(request)
    }
}