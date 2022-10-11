package com.example.griffinpiece

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var actionBar: ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        actionBar = supportActionBar!!
        actionBar.title = "Connecter-vous à votre compte"
        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#040404")))

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        var sentEmail = this.intent.getStringExtra("email")
        var sentUsername = this.intent.getStringExtra("username")

        var loginEmail: EditText = findViewById(R.id.loginEmail)
        var loginUsername: EditText = findViewById(R.id.loginUsername)
        var loginPassword: EditText = findViewById(R.id.loginPassword)
        var clickHere: TextView = findViewById(R.id.clickHereSignUp)

        var btnLogin: Button = findViewById(R.id.btnSignUp)
        val intentUserLogin = Intent(this, MainActivity::class.java)
        var intentCreateAccount = Intent(this, CreateAccount::class.java)

        loginEmail.setText(sentEmail)
        loginUsername.setText(sentUsername)

        btnLogin.setOnClickListener{

            val queue = Volley.newRequestQueue(this)

            val url = "http://172.105.104.199:3000/auth/login"

            val jsonobject = JSONObject()

            jsonobject.put("email", loginEmail.text)
            jsonobject.put("password", loginPassword.text)
            jsonobject.put("username", loginUsername.text)

            val request = JsonObjectRequest(
                Request.Method.POST,url, jsonobject,
                Response.Listener {
                    Log.i("email",jsonobject.getString("email"))
                    Log.i("username", jsonobject.getString("username"))
                    intentUserLogin.putExtra("email", jsonobject.getString("email"))
                    intentUserLogin.putExtra("username",jsonobject.getString("username"))
                    Toast.makeText(this,"Votre utilisateur est connecté! ${loginEmail.getText()} ", Toast.LENGTH_SHORT).show()
                    this.startActivity(intentUserLogin)

                }, Response.ErrorListener {
                    Log.e("ERROR",it.toString())
                })
            queue.add(request)
        }


        clickHere.setOnClickListener{
            this.startActivity(intentCreateAccount)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}