package com.example.griffinpiece

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar
import com.example.griffinpiece.ui.login.LoginActivity
import com.example.griffinpiece.ui.register.SignUpActivity

class FirstPageActivity : AppCompatActivity() {

    private lateinit var actionBar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firstpage)

        actionBar = supportActionBar!!
        actionBar.title = "Bienvenue sur Griffin Piece"
        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#040404")))

        var login = findViewById<Button>(R.id.btnLogin)
        login.setOnClickListener {
            startActivity(Intent(this,  LoginActivity::class.java))
        }

        var signup = findViewById<Button>(R.id.btnSignUp)
        signup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}