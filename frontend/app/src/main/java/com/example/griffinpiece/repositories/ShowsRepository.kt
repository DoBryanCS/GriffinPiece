package com.example.griffinpiece.repositories

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.griffinpiece.MainActivity.Companion.SRVURL
import com.example.griffinpiece.models.Show
import com.google.gson.Gson

class ShowsRepository(private val application: Application) {
    fun getShows(shows: MutableLiveData<MutableList<Show>>) {

        val queue = Volley.newRequestQueue(application)
        val r = StringRequest(
            Request.Method.GET,
            "$SRVURL/show",
            {
                val arrayShows = Gson().fromJson(it, Array<Show>::class.java)
                shows.value = arrayShows.toMutableList()
            },
            {
                println("ERREUR: /api/show")
            })
        queue.add(r)
    }
}