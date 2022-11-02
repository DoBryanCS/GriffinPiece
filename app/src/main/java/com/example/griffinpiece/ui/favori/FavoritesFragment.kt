package com.example.griffinpiece.ui.favori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.griffinpiece.R
import com.example.griffinpiece.adapters.ShowsFavoritesRecyclerViewAdapter

class FavoritesFragment : Fragment() {

    private lateinit var rvListeShow: RecyclerView

    private lateinit var favoritesViewModel: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.rvListeShow = view.findViewById(R.id.rvListeShows)
        this.favoritesViewModel =
            ViewModelProvider(this).get(FavoritesViewModel::class.java)
        this.rvListeShow.layoutManager = LinearLayoutManager(activity)

        favoritesViewModel.shows.observe(viewLifecycleOwner) {
            this.rvListeShow.adapter = ShowsFavoritesRecyclerViewAdapter(it)

        }
    }
}