package com.example.griffinpiece.adapters

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.griffinpiece.R
import com.example.griffinpiece.models.Season
import com.squareup.picasso.Picasso

class SeasonsRecyclerViewAdapter (private val datasetSeason: MutableList<Season>):
    RecyclerView.Adapter<SeasonsRecyclerViewAdapter.SeasonViewHolder>(){

        class SeasonViewHolder(val view:View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_seasons_item, parent, false)
        return SeasonViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        val imgSeason = holder.view.findViewById<ImageView>(R.id.imgSeason)
        Picasso.get().load(datasetSeason[position].imageUrl).into(imgSeason)

        val titleSeason = holder.view.findViewById<TextView>(R.id.titleSeason)
        titleSeason.setText(datasetSeason[position].title)

        holder.view.setOnClickListener {
            val seasonId = bundleOf(Pair("seasonId", datasetSeason[position].seasonId))
            Log.i("Id", seasonId.toString())
            holder.view.findNavController().navigate(R.id.navigation_season,seasonId)
        }
    }

    override fun getItemCount(): Int {
        return datasetSeason.size
    }




}