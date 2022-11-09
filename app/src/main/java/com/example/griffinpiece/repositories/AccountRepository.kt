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
import com.example.griffinpiece.models.User
import com.google.gson.Gson
import org.json.JSONObject

class AccountRepository(private val application: Application) {
    fun getUser(showUser: MutableLiveData<User>) {

        val queue = Volley.newRequestQueue(application)
        val r = object : StringRequest(
            Request.Method.GET,
            "${SRVURL}/user",
            {
                val arrayUser = Gson().fromJson(it, User::class.java)
                showUser.value = arrayUser
                println(it)
            },
            {
                println("ERREUR: /api/user")
            }
        )
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headerMap = mutableMapOf<String, String>()
                headerMap.put("Content-Type", "application/json")
                headerMap.put("Authorization", "Bearer $TOKEN")
                return headerMap
            }
        }
        queue.add(r)
    }

    fun changeEmail(currentPassword: String, newEmail: String, success: MutableLiveData<Boolean>) {
        val url = "$SRVURL/user/email"

        val queue = Volley.newRequestQueue(application)
        val jsonbody = JSONObject()
        jsonbody.put("currentPassword", currentPassword)
        jsonbody.put("email", newEmail)

        val request = object : JsonObjectRequest(
            Request.Method.PUT, url, jsonbody,
            Response.Listener {
                success.value = true
                Toast.makeText(application, "Changement de l'email avec succes!", Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener {
                success.value = false
                Toast.makeText(application, "Erreur durant le processus de mise a jour de l'email!" , Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headerMap = mutableMapOf<String, String>()
                headerMap.put("Content-Type", "application/json")
                headerMap.put("Authorization", "Bearer $TOKEN")
                return headerMap
            }
        }
        queue.add(request)
    }

    fun changePassword(currentPassword: String, newPassword: String, success: MutableLiveData<Boolean>) {
        val url = "$SRVURL/user/password"

        val queue = Volley.newRequestQueue(application)
        val jsonbody = JSONObject()
        jsonbody.put("password", newPassword)
        jsonbody.put("currentPassword", currentPassword)

        val request = object : JsonObjectRequest(
            Request.Method.PUT, url, jsonbody,
            Response.Listener {
                success.value = true
                Toast.makeText(application, "Changement du password avec succes!", Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener {
                success.value = false
                Toast.makeText(application, "Erreur durant le processus de mise a jour du password!" , Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headerMap = mutableMapOf<String, String>()
                headerMap.put("Content-Type", "application/json")
                headerMap.put("Authorization", "Bearer $TOKEN")
                return headerMap
            }
        }
        queue.add(request)
    }
}