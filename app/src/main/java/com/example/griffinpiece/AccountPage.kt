package com.example.griffinpiece

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AccountPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_fourth)

        val txtViewEmail: TextView = findViewById(R.id.txtViewEmail)
        val txtViewUsername: TextView = findViewById(R.id.txtViewUsername)


        val loginEmail = this.intent.getStringExtra("Email")
        val loginUsername = this.intent.getStringExtra("Username")
        txtViewEmail.text = loginEmail
        txtViewUsername.text = loginUsername


    }
}