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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EmissionPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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

        val emissionViewModel = ViewModelProvider(this).get(EmissionViewModel::class.java)
        this.rvEmissionSeasons = view.findViewById<RecyclerView>(R.id.seasonEpisodes)

        val imgEpisode = view.findViewById<ImageView>(R.id.seasonImg)
        val titleEmission = view.findViewById<TextView>(R.id.seasonTitle)

        val ratingBar = view.findViewById<RatingBar>(R.id.RatingBar)


        this.rvEmissionSeasons.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)

        val showid = this.requireArguments().get("id")
        emissionViewModel.id.value = showid as Int?

        emissionViewModel.details.observe(/* owner = */ viewLifecycleOwner) {
            Picasso.get().load(it.imageUrl).into(imgEpisode)
            titleEmission.setText(it.title)
            ratingBar.rating = it.rating
        }

        emissionViewModel.datasetSeasons.observe(viewLifecycleOwner) {
            this.rvEmissionSeasons.adapter = SeasonsRecyclerViewAdapter(it)
        }





    }
}