package com.example.griffinpiece.ui.compte

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.griffinpiece.MainActivity
import com.example.griffinpiece.R
import com.example.griffinpiece.ui.login.LoginActivity

class ChangeEmailActivity : AppCompatActivity() {
    private lateinit var actionBar: ActionBar
    private lateinit var changeEmailViewModel: ChangeEmailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changeemail)

        this.changeEmailViewModel =
            ViewModelProvider(this).get(ChangeEmailViewModel::class.java)

        actionBar = supportActionBar!!
        actionBar.title = "Changer l'email"

        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#040404")))

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        var formCurrentEmail: EditText = findViewById(R.id.emailEt)
        var formNewEmail: EditText = findViewById(R.id.newemailEt)
        var formConfirmEmail: EditText = findViewById(R.id.confirmemailEt)
        var btnChangeEmail: Button = findViewById(R.id.changeEmail)

        val intentConnected = Intent(application, MainActivity::class.java)
        btnChangeEmail.setOnClickListener {
//            changeEmailViewModel.email.value = formNewEmail.text.toString()
//            if (formCurrentEmail.text.toString() != changeEmailViewModel.userInfo.value?.email.toString()) {
//                formCurrentEmail.error = "Email invalide!"
//            } else if(formConfirmEmail.text.toString() != formNewEmail.text.toString()) {
//                formConfirmEmail.error = "Les emails ne sont pas identiques!"
//            } else {
//                changeEmailViewModel.changeEmail()
//                this.startActivity(intentConnected)
//            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}