package com.example.griffinpiece.repositories

import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.griffinpiece.MainActivity.Companion.SRVURL
import com.example.griffinpiece.ui.login.LoginActivity
import com.example.griffinpiece.ui.login.LoginViewModel
import org.json.JSONObject

class SignUpRepository(val app: Application) {
    fun signUp (email: String, username: String, password: String, success: MutableLiveData<Boolean>) {
        val url = "$SRVURL/auth/register"

        val queue = Volley.newRequestQueue(app)
        val jsonbody = JSONObject()
        jsonbody.put("email", email)
        jsonbody.put("username", username)
        jsonbody.put("password",password)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonbody,
            {
                Toast.makeText(app, "L'utilisateur ${username} a été créee", Toast.LENGTH_SHORT).show()
                success.value = true
              },
            {
                Toast.makeText(app, "Erreur durant le processus de création d'utilisateur !" , Toast.LENGTH_SHORT).show()
                success.value = false
            }
        )
        queue.add(request)
    }
}