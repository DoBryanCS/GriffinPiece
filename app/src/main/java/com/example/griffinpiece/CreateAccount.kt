package com.example.griffinpiece

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar

class CreateAccount : AppCompatActivity() {
    private lateinit var actionBar: ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionBar = supportActionBar!!
        actionBar.title = "Créer un compte"

        var formEmail: EditText = findViewById(R.id.loginEmail)
        var formUsername: EditText = findViewById(R.id.loginUsername)
        var formPasswprd: EditText = findViewById(R.id.loginPassword)
        var clickHere: TextView = findViewById(R.id.clickHereSignUp)

        var btnCreateAccount: Button = findViewById(R.id.btnLogin)

        var intentLoginAccount = Intent(this, LoginActivity::class.java)
        btnCreateAccount.setOnClickListener{
            Toast.makeText(this,"Votre utilisateur a été crée! ${formEmail.getText()} ", Toast.LENGTH_SHORT).show()
        }

        clickHere.setOnClickListener{
            this.startActivity(intentLoginAccount)
        }



    }
}