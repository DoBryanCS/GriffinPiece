package com.example.griffinpiece.ui.register

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import com.example.griffinpiece.R
import com.example.griffinpiece.ui.login.LoginActivity

class SignUpActivity : AppCompatActivity() {
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

        val formEmail: EditText = findViewById(R.id.loginEmail)
        val formUsername: EditText = findViewById(R.id.loginUsername)
        val formPassword: EditText = findViewById(R.id.loginPassword)
        val btnToLoginActivity: TextView = findViewById(R.id.clickHereLogin)


        val btnCreateAccount: Button = findViewById(R.id.btnSignUp)

        // intents
        val intentLoginActivity = Intent(this, LoginActivity::class.java)

        signUpViewModel.success.observe(this) {
            if (it) {
                val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.apply() {
                    putString("STRING_KEY", formEmail.text.toString())
                }.apply()
                this.startActivity(intentLoginActivity)
            }
        }


        btnCreateAccount.setOnClickListener{
            signUpViewModel.signUp(formEmail.text.toString(), formUsername.text.toString(), formPassword.text.toString())

        }

        btnToLoginActivity.setOnClickListener{
            this.startActivity(intentLoginActivity)
        }



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}