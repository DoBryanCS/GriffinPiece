package com.example.griffinpiece.ui.accueil

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.griffinpiece.R
import com.example.griffinpiece.adapters.ShowsRecyclerViewAdapter

class FirstFragment:Fragment() {
    private lateinit var rvListeShow: RecyclerView

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_first, container, false)
        val activity = activity as Context
        this.rvListeShow = view.findViewById(R.id.rvListeShows)
        val ShowViewModel = ViewModelProvider(this).get(ShowViewModel::class.java)
        this.rvListeShow.layoutManager = GridLayoutManager(activity, 2)

        ShowViewModel.shows.observe(viewLifecycleOwner) {
            this.rvListeShow.adapter = ShowsRecyclerViewAdapter(it)

        }
        return view
    }

}