package com.example.griffinpiece.repositories

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.griffinpiece.MainActivity
import com.example.griffinpiece.MainActivity.Companion.SRVURL
import com.example.griffinpiece.MainActivity.Companion.TOKEN
import org.json.JSONObject

class LoginRepository (private val application: Application){
    fun login(email: String?, password:String?) {



        val queue = Volley.newRequestQueue(application)

        val url = SRVURL + "/auth/login"

        val jsonbody = JSONObject()
        jsonbody.put("email",email)
        jsonbody.put("password",password)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonbody,
            Response.Listener{
                TOKEN = it.getString("token")

            },
            Response.ErrorListener {
                Log.e("Erreur!", it.toString())
            }
        )

        queue.add(request)

    }

}
