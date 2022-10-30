package com.example.griffinpiece.ui.login

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import com.example.griffinpiece.ui.register.SignUpActivity
import com.example.griffinpiece.MainActivity
import com.example.griffinpiece.MainActivity.Companion.TOKEN
import com.example.griffinpiece.R
import java.util.*
import kotlin.concurrent.schedule

class LoginActivity : AppCompatActivity() {
    private lateinit var actionBar: ActionBar
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var loginEmail: EditText = findViewById(R.id.loginEmail)
        var loginPassword: EditText = findViewById(R.id.loginPassword)
        var clickHere: TextView = findViewById(R.id.clickHereSignUp)

        var btnLogin: Button = findViewById(R.id.btnLogin)
        var intentCreateAccount = Intent(this, SignUpActivity::class.java)

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY", null)

        loginEmail.setText(savedString)

        this.loginViewModel =
            ViewModelProvider(this).get(LoginViewModel::class.java)

        actionBar = supportActionBar!!
        actionBar.title = "Connecter-vous à votre compte"
        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#040404")))

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        val intentConnected = Intent(application, MainActivity::class.java)

        fun userAuthentification() {
            if (TOKEN == "") {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(
                        applicationContext,
                        "Erreur d'authentification",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                this.startActivity(intentConnected)
            }
        }

        btnLogin.setOnClickListener {
            loginViewModel.email.value = loginEmail.text.toString()
            loginViewModel.password.value = loginPassword.text.toString()
            loginViewModel.login()
            Timer().schedule(500) {
                userAuthentification()
            }
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
