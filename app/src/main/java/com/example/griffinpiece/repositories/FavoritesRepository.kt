package com.example.griffinpiece.repositories

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.griffinpiece.MainActivity.Companion.SRVURL
import com.example.griffinpiece.MainActivity.Companion.TOKEN
import com.example.griffinpiece.models.IsFavorite
import com.example.griffinpiece.models.Show
import com.google.gson.Gson

class FavoritesRepository(private val application: Application) {
    fun getFavorites(shows: MutableLiveData<MutableList<Show>>) {

        val queue = Volley.newRequestQueue(application)
        val r = object : StringRequest(
            Request.Method.GET,
            "${SRVURL}/favorites",
            {
                val arrayShows = Gson().fromJson(it, Array<Show>::class.java)
                shows.value = arrayShows.toMutableList()
                Log.d("info :", shows.value.toString())
            },
            {
                println("ERREUR: /api/show")
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headerMap = mutableMapOf<String, String>()
                headerMap.put("Content-Type", "application/json")
                headerMap.put("Authorization", "Bearer $TOKEN")
                return headerMap
            }
        }
        queue.add(r)
    }

    fun getFavorite(showFavorite: MutableLiveData<IsFavorite>, id: Int) {

        val queue = Volley.newRequestQueue(application)
        val r = object : StringRequest(
            Request.Method.GET,
            "${SRVURL}/favorite/$id",
            {
                val arrayFavorite = Gson().fromJson(it, IsFavorite::class.java)
                showFavorite.value = arrayFavorite
                println(it)
            },
            {
                println("ERREUR: /api/show")
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headerMap = mutableMapOf<String, String>()
                headerMap.put("Content-Type", "application/json")
                headerMap.put("Authorization", "Bearer $TOKEN")
                return headerMap
            }
        }
        queue.add(r)
    }

    fun postFavorite(id: Int) {

        val queue = Volley.newRequestQueue(application)
        val r = object : StringRequest(
            Request.Method.POST,
            "${SRVURL}/favorite/$id",
            {
                Log.d("Response", it.toString())
            },
            {
                println("ERREUR: /api/show")
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headerMap = mutableMapOf<String, String>()
                headerMap.put("Content-Type", "application/json")
                headerMap.put("Authorization", "Bearer $TOKEN")
                return headerMap
            }
        }
        queue.add(r)
    }

    fun deleteFavorite(id: Int) {

        val queue = Volley.newRequestQueue(application)
        val r = object : StringRequest(
            Request.Method.DELETE,
            "${SRVURL}/favorite/$id",
            {
                Toast.makeText(application, "L'émission a été enlevée de vos favoris!", Toast.LENGTH_SHORT).show()
                Log.d("Response", it.toString())
            },
            {
                println("ERREUR: /api/show")
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headerMap = mutableMapOf<String, String>()
                headerMap.put("Content-Type", "application/json")
                headerMap.put("Authorization", "Bearer $TOKEN")
                return headerMap
            }
        }
        queue.add(r)
    }
}