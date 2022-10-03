package com.example.griffinpiece

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar

class LoginActivity : AppCompatActivity() {
    private lateinit var actionBar: ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        actionBar = supportActionBar!!
        actionBar.title = "Connecter-vous à votre compte"

        var loginEmail: EditText = findViewById(R.id.loginEmail)
        var loginUsername: EditText = findViewById(R.id.loginUsername)
        var loginPassword: EditText = findViewById(R.id.loginPassword)
        var clickHere: TextView = findViewById(R.id.clickHereSignUp)

        var btnLogin: Button = findViewById(R.id.btnLogin)
        var intentCreateAccount = Intent(this, CreateAccount::class.java)

        btnLogin.setOnClickListener{
            Toast.makeText(this,"Votre utilisateur a été crée! ${loginEmail.getText()} ", Toast.LENGTH_SHORT).show()
        }

        clickHere.setOnClickListener{
            this.startActivity(intentCreateAccount)
        }

    }
}