package com.example.griffinpiece.repositories

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.griffinpiece.models.Show
import com.google.gson.Gson

class ShowRepository(private val application: Application) {
    fun getShows(shows: MutableLiveData<MutableList<Show>>) {

        val queue = Volley.newRequestQueue(application)
        val r = StringRequest(
            Request.Method.GET,
            "http://172.105.104.199/show",
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