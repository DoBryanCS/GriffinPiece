package com.example.griffinpiece.ui.register

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.griffinpiece.R
import com.example.griffinpiece.ui.login.LoginActivity
import com.example.griffinpiece.ui.login.LoginViewModel
import org.json.JSONObject

class CreateAccount : AppCompatActivity() {
    private lateinit var actionBar: ActionBar
    private lateinit var signUpViewModel: SignUpViewModel
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        this.signUpViewModel =
            ViewModelProvider(this).get(SignUpViewModel::class.java)

        actionBar = supportActionBar!!
        actionBar.title = "Créer un compte"
        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#040404")))

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        var formEmail: EditText = findViewById(R.id.loginEmail)
        var formUsername: EditText = findViewById(R.id.loginUsername)
        var formPassword: EditText = findViewById(R.id.loginPassword)
        var clickHere: TextView = findViewById(R.id.clickHereLogin)


        var btnCreateAccount: Button = findViewById(R.id.btnSignUp)

        var intentLoginAccount = Intent(this, LoginActivity::class.java)
        btnCreateAccount.setOnClickListener{
            signUpViewModel.email.value = formEmail.text.toString()
            signUpViewModel.username.value = formUsername.text.toString()
            signUpViewModel.password.value = formPassword.text.toString()
            signUpViewModel.signUp()
            val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply() {
                putString("STRING_KEY", formEmail.text.toString())
            }.apply()
            this.startActivity(intentLoginAccount)
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