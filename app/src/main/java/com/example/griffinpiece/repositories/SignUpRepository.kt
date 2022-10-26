package com.example.griffinpiece.repositories

import android.app.Application
import android.content.Intent
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.griffinpiece.MainActivity.Companion.SRVURL
import com.example.griffinpiece.viewmodels.user.LoginViewModel
import org.json.JSONObject

class SignUpRepository(val app: Application) {
    val intentCreatedAccount = Intent(app, LoginViewModel::class.java)
    fun signUp (email: String?, username: String?, password: String?) {
        val url = SRVURL + "/auth/register"

        val queue = Volley.newRequestQueue(app)
        val jsonbody = JSONObject()
        jsonbody.put("email", email)
        jsonbody.put("username", username)
        jsonbody.put("password",password)

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonbody,
            Response.Listener {
                Toast.makeText(app, "L'utilisateur ${it.getString("username")} a été créee", Toast.LENGTH_SHORT).show()
                intentCreatedAccount.putExtra("username", it.getString("username"))
                app.startActivity(intentCreatedAccount)
              },
            Response.ErrorListener {
                Toast.makeText(app, "Erreur durant le processus de création d'utilisateur !" , Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(request)
    }
}