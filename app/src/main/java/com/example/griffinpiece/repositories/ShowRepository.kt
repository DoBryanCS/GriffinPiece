package com.example.griffinpiece.repositories

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.griffinpiece.MainActivity.Companion.SRVURL
import com.example.griffinpiece.MainActivity.Companion.TOKEN
import com.example.griffinpiece.models.Season
import com.example.griffinpiece.models.Show
import com.google.gson.Gson

class ShowRepository (private val application: Application) {
    fun getShowDetails(
        id: Int, show :MutableLiveData<Show>) {
        val url = SRVURL + "/show/${id}"
        val queue = Volley.newRequestQueue(application)

        val request = StringRequest(
            Request.Method.GET, url,
            {
                val gson = Gson()
                show.value = gson.fromJson(it, Show::class.java)
            },
            {
                Toast.makeText(application, "Erreur dans la requête pour afficher les details du show", Toast.LENGTH_SHORT).show()
                Log.e("Erreur", it.toString())
            }
        )
        queue.add(request)
    }


    fun getSeasons(showId: Int ,datasetSeasons: MutableLiveData<MutableList<Season>>) {
        val url = SRVURL + "/seasons/${showId}"
        val queue = Volley.newRequestQueue(application)

        val request = StringRequest(
            Request.Method.GET,url,
            {
                val gson = Gson()
                val seasonsArray = gson.fromJson(it, Array<Season>::class.java)
                datasetSeasons.value =seasonsArray.toMutableList()
            },
            {
                Log.e("Erreur", it.toString())
            }
        )
        queue.add(request)
    }

    fun getFavorite(id: Int, isFavorite:MutableLiveData<Boolean>) {
        var url = SRVURL + "/favorite/${id}"
        val queue = Volley.newRequestQueue(application)

        val request = object: JsonObjectRequest (
            Request.Method.GET, url, null,
            Response.Listener {
                isFavorite.value = it.getBoolean("isFavorite")
            },
            Response.ErrorListener {  }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headerMap = mutableMapOf<String, String>()
                headerMap.put("Content-Type","application/json")
                headerMap.put("Authorization", "Bearer ${TOKEN}")
                return headerMap
            }
        }
        queue.add(request)
        }

    fun deleteFavorite(id: MutableLiveData<Int>, isFavorite: MutableLiveData<Boolean>) {
        val url =  SRVURL + "/favorite/${id.value}"
        val queue = Volley.newRequestQueue(application)
        val request = object :StringRequest (
            Request.Method.DELETE, url,
            Response.Listener {
                isFavorite.value = false
                Toast.makeText(application, "Le show a été enlevée de vos favoris D:", Toast.LENGTH_SHORT).show()

            },
            Response.ErrorListener {
                Toast.makeText(application, it.toString(), Toast.LENGTH_SHORT).show()
                Log.i("Erreur", it.toString())
            },
        ){
            override fun getHeaders(): MutableMap<String, String> {
                val headerMap = mutableMapOf<String, String>()
                headerMap.put("Content-Type", "application/json")
                headerMap.put("Authorization", "Bearer ${TOKEN}")
                return headerMap
            }
        }
        queue.add(request)
    }
}