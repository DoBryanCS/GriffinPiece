package com.example.griffinpiece.ui.Season

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.griffinpiece.R
import com.example.griffinpiece.adapters.EpisodesRecyclerViewAdapter
import com.squareup.picasso.Picasso

class SeasonFragment : Fragment() {


    private lateinit var viewModel: SeasonViewModel
    private lateinit var episodeRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_season, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val seasonViewModel = ViewModelProvider(this).get(SeasonViewModel::class.java)
        this.episodeRecyclerView = view.findViewById(R.id.rvSeasonEpisodes)
        this.episodeRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        val seasonImg = view.findViewById<ImageView>(R.id.seasonImg)
        val seasonTitle = view.findViewById<TextView>(R.id.seasonTitle)
        val showTitle = view.findViewById<TextView>(R.id.showTitle)

        val seasonId = this.requireArguments().get("seasonId") as Int
        seasonViewModel.getData(seasonId)
        Log.i("season", seasonId.toString())

        seasonViewModel.detailsSeason.observe(viewLifecycleOwner) {
            Picasso.get().load(it.imageUrl).into(seasonImg)
            seasonTitle.setText(it.title)
            showTitle.setText(it.showTitle)
        }

        seasonViewModel.datasetEpisode.observe(viewLifecycleOwner) {
            this.episodeRecyclerView.adapter = EpisodesRecyclerViewAdapter(it)
        }




    }

}