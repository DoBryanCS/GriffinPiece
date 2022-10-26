package com.example.griffinpiece

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class CreateAccount : AppCompatActivity() {
    private lateinit var actionBar: ActionBar
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        actionBar = supportActionBar!!
        actionBar.title = "Créer un compte"
        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#040404")))

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        var formEmail: EditText = findViewById(R.id.loginEmail)
        var formUsername: EditText = findViewById(R.id.loginUsername)
        var formPassword: EditText = findViewById(R.id.loginPassword)
        var clickHere: TextView = findViewById(R.id.clickHereSignUp)


        var btnCreateAccount: Button = findViewById(R.id.btnSignUp)

        var intentLoginAccount = Intent(this, LoginActivity::class.java)
        btnCreateAccount.setOnClickListener{

            val queue = Volley.newRequestQueue(this)

            val url = "http://172.105.104.199/auth/register"

            val jsonobject = JSONObject()

            jsonobject.put("email", formEmail.text)
            jsonobject.put("password", formPassword.text)
            jsonobject.put("username", formUsername.text)

            val request = JsonObjectRequest(Request.Method.POST,url, jsonobject,
                Response.Listener {
                    Log.i("email",jsonobject.getString("email"))
                    Log.i("username", jsonobject.getString("username"))
                    intentLoginAccount.putExtra("email", jsonobject.getString("email"))
                    intentLoginAccount.putExtra("username",jsonobject.getString("username"))
                    this.startActivity(intentLoginAccount)
                },

                Response.ErrorListener {
                    Log.i("ERROR", it.toString())
                })
            queue.add(request)
        }


        clickHere.setOnClickListener{
            this.startActivity(intentLoginAccount)
        }



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}