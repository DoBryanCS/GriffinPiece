package com.example.griffinpiece.ui.compte

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.griffinpiece.MainActivity
import com.example.griffinpiece.R
import com.example.griffinpiece.models.User
import com.example.griffinpiece.repositories.AccountRepository
import com.example.griffinpiece.ui.favori.FavoritesViewModel

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var actionBar: ActionBar
    private lateinit var changePasswordViewModel: ChangePasswordViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changepassword)

        actionBar = supportActionBar!!
        actionBar.title = "Changer le mot de passe"

        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#040404")))

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        changePasswordViewModel = ViewModelProvider(this).get(ChangePasswordViewModel::class.java)

        val formCurrentPassword: EditText = findViewById(R.id.passwordEt)
        val formNewPassword: EditText = findViewById(R.id.newpasswordEt)
        val formConfirmPassword: EditText = findViewById(R.id.confirmpasswordEt)
        val btnChangePassword: Button = findViewById(R.id.changePassword)


        val intentConnected = Intent(application, MainActivity::class.java)

        changePasswordViewModel.success.observe(this) {
            if (it) {
                this.startActivity(intentConnected)
            }
            else {
                Toast.makeText(this, "Erreur durant le processus de mise a jour du password!" , Toast.LENGTH_SHORT).show()
            }
        }
        btnChangePassword.setOnClickListener {
            val currentPassword : String = formCurrentPassword.text.toString()
            val newPassword : String = formNewPassword.text.toString()
            val newPasswordConfirm : String = formConfirmPassword.text.toString()

            if(newPasswordConfirm != newPassword) {
                formConfirmPassword.error = "Les passwords ne sont pas identiques!"
            } else {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Etes-vous sur de vouloir changer le mot de passe?")
                    .setCancelable(false)
                    .setPositiveButton("Oui") { dialog, which ->
                        this.changePasswordViewModel.changePassword(currentPassword, newPassword)
                    }
                    .setNegativeButton("Non") { dialog, which ->
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
                val nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE)
                nbutton.setTextColor(Color.parseColor("#FF5722"))
                val pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE)
                pbutton.setTextColor(Color.parseColor("#FF5722"))
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}