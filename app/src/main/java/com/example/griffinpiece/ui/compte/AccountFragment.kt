package com.example.griffinpiece.ui.compte

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.griffinpiece.R
import com.example.griffinpiece.ui.login.LoginActivity

class AccountFragment : Fragment() {

    private lateinit var btnChangeEmail: Button
    private lateinit var btnChangePassword: Button
    private lateinit var tvUsername: TextView
    private lateinit var accountViewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.accountViewModel =
            ViewModelProvider(this).get(AccountViewModel::class.java)
        btnChangeEmail = view.findViewById(R.id.btnChangeEmail)
        tvUsername = view.findViewById(R.id.txtViewUsername)
        btnChangePassword = view.findViewById(R.id.btnChangePassword)
        tvUsername.setText(accountViewModel.userInfo.value?.username.toString())
        btnChangeEmail.setOnClickListener {
            val intent = Intent (getActivity(), ChangeEmailActivity::class.java)
            getActivity()?.startActivity(intent)
        }
        btnChangePassword.setOnClickListener {
            val intent = Intent (getActivity(), ChangePasswordActivity::class.java)
            getActivity()?.startActivity(intent)
        }
    }

}