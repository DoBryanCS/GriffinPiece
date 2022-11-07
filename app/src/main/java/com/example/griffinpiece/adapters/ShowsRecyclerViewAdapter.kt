package com.example.griffinpiece.adapters

import android.annotation.SuppressLint
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
import com.example.griffinpiece.models.Show
import com.squareup.picasso.Picasso

class ShowsRecyclerViewAdapter(private val datasetShows: MutableList<Show>) :
    RecyclerView.Adapter<ShowsRecyclerViewAdapter.ShowViewHolder>() {

    class ShowViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_shows_item, parent, false)
        return ShowViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holderShow: ShowViewHolder, position: Int) {
        holderShow.view.findViewById<TextView>(R.id.tvShow).text =
            this.datasetShows[position].title

        val imgShow = holderShow.view.findViewById<ImageView>(R.id.imgShow)
        Picasso.get().load(this.datasetShows[position].imageUrl).into(imgShow)

        holderShow.view.setOnClickListener {
            val id = bundleOf(Pair("id", datasetShows[position].id))
            Log.i("id", id.toString())
            holderShow.view.findNavController().navigate(R.id.navigation_emission,id)
        }

    }

    override fun getItemCount(): Int = this.datasetShows.size
}