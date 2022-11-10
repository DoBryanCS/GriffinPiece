package com.example.griffinpiece.adapters

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.griffinpiece.R
import com.example.griffinpiece.models.Show
import com.example.griffinpiece.ui.favori.FavoritesViewModel
import com.squareup.picasso.Picasso


class ShowsFavoritesRecyclerViewAdapter(private val datasetShows: MutableList<Show>, val favoritesViewModel: FavoritesViewModel) :
    RecyclerView.Adapter<ShowsFavoritesRecyclerViewAdapter.ShowViewHolder>() {

    class ShowViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fagment_favorites_item, parent, false)
        return ShowViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holderShow: ShowViewHolder, position: Int) {
        holderShow.view.findViewById<TextView>(R.id.titleTv).text =
            this.datasetShows[position].title
        val imgShow = holderShow.view.findViewById<ImageView>(R.id.imgShowFavorite)
        Picasso.get().load(this.datasetShows[position].imageUrl).into(imgShow)
        holderShow.view.setOnClickListener {
            val id = bundleOf(Pair("id", datasetShows[position].id))
            holderShow.view.findNavController().navigate(R.id.navigation_emission,id)
        }
        val btnFavorite = holderShow.view.findViewById<Button>(R.id.btnFvorite)
        btnFavorite.setOnClickListener {
            val builder = AlertDialog.Builder(holderShow.view.context)
            builder.setMessage("Enlever l'émission des favoris?")
                .setCancelable(false)
                .setPositiveButton("Oui") { dialog, which ->
                    val id = datasetShows[position].id
                    this.favoritesViewModel.id = id
                    println(this.favoritesViewModel.id)
                    this.favoritesViewModel.delete()

                    datasetShows.removeAt(position)
                    this.notifyDataSetChanged()
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

    override fun getItemCount(): Int = this.datasetShows.size
}