package com.example.griffinpiece.ui.Emission

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.griffinpiece.IOnBackPressed
import com.example.griffinpiece.R
import com.example.griffinpiece.adapters.SeasonsRecyclerViewAdapter
import com.squareup.picasso.Picasso

class EmissionPageFragment : Fragment(), IOnBackPressed {
    private lateinit var rvEmissionSeasons: RecyclerView

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_emission_page, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.rvEmissionSeasons = view.findViewById<RecyclerView>(R.id.rvSeasonEpisodes)

        val imgEpisode = view.findViewById<ImageView>(R.id.seasonImg)
        val titleEmission = view.findViewById<TextView>(R.id.seasonTitle)

        val ratingBar = view.findViewById<RatingBar>(R.id.RatingBar)
        val favoriteButton = view.findViewById<ImageView>(R.id.btnFavorite)



        this.rvEmissionSeasons.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)

        val showid : Int = this.requireArguments().get("id") as Int

        val emissionViewModel = ViewModelProvider(this).get(EmissionViewModel::class.java)
        emissionViewModel.getData(showid)


        favoriteButton.setOnClickListener {
            if (emissionViewModel.isFavorite.value == true) {
                val builder = AlertDialog.Builder(view.context)
                builder.setMessage("Enlever l'émission des favoris?")
                    .setCancelable(false)
                    .setPositiveButton("Oui") { dialog, which ->
                        emissionViewModel.deleteFavorite(showid)
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
            } else {
                val builder = AlertDialog.Builder(view.context)
                builder.setMessage("Ajouter l'émission aux favoris?")
                    .setCancelable(false)
                    .setPositiveButton("Oui") { dialog, which ->
                        emissionViewModel.addFavorite(showid)
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

        emissionViewModel.show.observe(viewLifecycleOwner) {
            Picasso.get().load(it.imageUrl).into(imgEpisode)
            titleEmission.text = it.title
            ratingBar.rating = it.rating
        }

        emissionViewModel.datasetSeasons.observe(viewLifecycleOwner) {
            this.rvEmissionSeasons.adapter = SeasonsRecyclerViewAdapter(it)
        }


        val heartFavorite = resources.getIdentifier("heart_isfavorite", "drawable", context?.packageName)
        val heartisNotFavorite = resources.getIdentifier("heart_white_notfavorite", "drawable", context?.packageName)



        emissionViewModel.isFavorite.observe(viewLifecycleOwner) {
            if (it == true) {
                favoriteButton.setImageDrawable(resources.getDrawable(heartFavorite))
            } else {
                favoriteButton.setImageDrawable(resources.getDrawable(heartisNotFavorite))
            }
        }

    }
    override fun onBackPressed() {}

}