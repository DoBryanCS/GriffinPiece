package com.example.griffinpiece.ui.Emission

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.griffinpiece.R
import com.example.griffinpiece.adapters.SeasonsRecyclerViewAdapter
import com.squareup.picasso.Picasso

class EmissionPageFragment : Fragment() {
    private lateinit var rvEmissionSeasons: RecyclerView

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_emission_page, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.rvEmissionSeasons = view.findViewById<RecyclerView>(R.id.seasonEpisodes)

        val imgEpisode = view.findViewById<ImageView>(R.id.seasonImg)
        val titleEmission = view.findViewById<TextView>(R.id.seasonTitle)

        val ratingBar = view.findViewById<RatingBar>(R.id.RatingBar)


        this.rvEmissionSeasons.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)

        val showid : Int = this.requireArguments().get("id") as Int

        val emissionViewModel = ViewModelProvider(this).get(EmissionViewModel::class.java)
        emissionViewModel.getData(showid)

        emissionViewModel.show.observe(viewLifecycleOwner) {
            Picasso.get().load(it.imageUrl).into(imgEpisode)
            titleEmission.text = it.title
            ratingBar.rating = it.rating
        }

        emissionViewModel.datasetSeasons.observe(viewLifecycleOwner) {
            this.rvEmissionSeasons.adapter = SeasonsRecyclerViewAdapter(it)
        }





    }
}