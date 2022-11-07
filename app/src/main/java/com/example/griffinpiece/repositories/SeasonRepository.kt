package com.example.griffinpiece.repositories

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.griffinpiece.MainActivity.Companion.SRVURL
import com.example.griffinpiece.models.Episode
import com.example.griffinpiece.models.Season
import com.google.gson.Gson

class SeasonRepository (private val application: Application) {
    fun getSeason(detailsSeason:MutableLiveData<Season> ,seasonId: MutableLiveData<Int>, showId: MutableLiveData<Int>, title: MutableLiveData<String>, description: MutableLiveData<String>, imageUrl: MutableLiveData<String>, releaseDate: MutableLiveData<String>) {
        val queue = Volley.newRequestQueue(application)
        Log.i("id", seasonId.value.toString())
        val url = SRVURL + "/season/${seasonId.value}"

        val request = StringRequest (
            Request.Method.GET, url ,
            Response.Listener {
                val gson = Gson()
                detailsSeason.value = gson.fromJson(it, Season::class.java)
                showId.value = detailsSeason.value?.showId
                title.value = detailsSeason.value?.title
                description.value = detailsSeason.value?.description
                imageUrl.value = detailsSeason.value?.imageUrl
                releaseDate.value = detailsSeason.value?.releaseDate
            },
            Response.ErrorListener {
                Log.e("Erreur", it.toString())
            }
        )

        queue.add(request)
    }

    fun getEpisodes(seasonId: MutableLiveData<Int>, datasetEpisodes: MutableLiveData<MutableList<Episode>>) {
        val queue = Volley.newRequestQueue(application)
        val url = SRVURL + "/episodes/${seasonId.value}"

        val request= StringRequest(
            Request.Method.GET, url,
            Response.Listener {
                val gson = Gson()
                var arrayEpisodes = gson.fromJson(it, Array<Episode>::class.java)
                datasetEpisodes.value = arrayEpisodes.toMutableList()
            },
            Response.ErrorListener {
                Log.e("Erreur", it.toString())
            }
        )

        queue.add(request)
    }
}