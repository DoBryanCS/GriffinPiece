package com.example.griffinpiece.adapters

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
import com.example.griffinpiece.models.Episode
import com.squareup.picasso.Picasso

class EpisodesRecyclerViewAdapter (private val datasetEpisodes: MutableList<Episode>) :

    RecyclerView.Adapter<EpisodesRecyclerViewAdapter.EpisodesViewHolder>() {
        class EpisodesViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_episode_item, parent, false)
        return EpisodesViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        val imgEpisode = holder.view.findViewById<ImageView>(R.id.imgEpisode)
        Picasso.get().load(datasetEpisodes[position].imageUrl).into(imgEpisode)
        val episodeTitle = holder.view.findViewById<TextView>(R.id.titleEpisode)
        episodeTitle.text = datasetEpisodes[position].title

        holder.view.setOnClickListener {
            val episodeId = bundleOf(Pair("episodeId", datasetEpisodes[position].id))
            Log.i("Id", episodeId.toString())
            holder.view.findNavController().navigate(R.id.navigation_season,episodeId)
        }
    }

    override fun getItemCount(): Int {
        return datasetEpisodes.size
    }

}


