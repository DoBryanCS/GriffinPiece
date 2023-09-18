package com.example.griffinpiece.repositories

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.griffinpiece.MainActivity.Companion.SRVURL
import com.example.griffinpiece.models.Episode
import com.google.gson.Gson

class EpisodeRepository (private val application: Application) {
    fun getEpisode(episodeId : Int, episodeInfo: MutableLiveData<Episode>) {
        val queue = Volley.newRequestQueue(application)
        Log.i("EpisodeId " , episodeId.toString())
        val url = SRVURL + "/episode/${episodeId}"

        val request = StringRequest (
            Request.Method.GET , url,
            {
                println(it)
                val gson = Gson()
                episodeInfo.value = gson.fromJson(it, Episode::class.java)
            },
            {
                Log.e("Erreur" , it.toString())
            }
        )
        queue.add(request)
    }
}