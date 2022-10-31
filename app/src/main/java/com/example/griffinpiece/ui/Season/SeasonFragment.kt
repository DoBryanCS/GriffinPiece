package com.example.griffinpiece.ui.Season

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.griffinpiece.R

class SeasonFragment : Fragment() {

    companion object {
        fun newInstance() = SeasonFragment()
    }

    private lateinit var viewModel: SeasonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_season2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SeasonViewModel::class.java)
        // TODO: Use the ViewModel
    }

}