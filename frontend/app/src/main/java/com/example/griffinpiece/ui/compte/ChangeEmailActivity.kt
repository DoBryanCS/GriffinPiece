package com.example.griffinpiece.ui.compte

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

        var formCurrentPassword: EditText = findViewById(R.id.passwordEt)
        var formNewEmail: EditText = findViewById(R.id.newemailEt)
        var formConfirmEmail: EditText = findViewById(R.id.confirmemailEt)
        var btnChangeEmail: Button = findViewById(R.id.changeEmail)

        val intentConnected = Intent(application, MainActivity::class.java)

        changeEmailViewModel.success.observe(this) {
            if (it) {
                this.startActivity(intentConnected)
            }
            else {
                Toast.makeText(this, "Erreur durant le processus de mise a jour du email!" , Toast.LENGTH_SHORT).show()
            }
        }

        btnChangeEmail.setOnClickListener {
            val currentPassword : String = formCurrentPassword.text.toString()
            val newEmail : String = formNewEmail.text.toString()
            val newEmailConfirm : String = formConfirmEmail.text.toString()

            if(newEmailConfirm != newEmail) {
                formConfirmEmail.error = "Les emails ne sont pas identiques!"
            } else {
                this.changeEmailViewModel.changeEmail(currentPassword, newEmail)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}