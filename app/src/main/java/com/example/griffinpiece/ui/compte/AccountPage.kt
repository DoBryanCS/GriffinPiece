package com.example.griffinpiece.ui.compte

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.griffinpiece.R

class AccountPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_account)

        val txtViewEmail: TextView = findViewById(R.id.txtViewEmail)
        val txtViewUsername: TextView = findViewById(R.id.txtViewUsername)


        val loginEmail = this.intent.getStringExtra("Email")
        val loginUsername = this.intent.getStringExtra("Username")
        txtViewEmail.text = loginEmail
        txtViewUsername.text = loginUsername


    }
}