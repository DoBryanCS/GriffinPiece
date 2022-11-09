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
import com.example.griffinpiece.R
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class SeasonFragment : Fragment() {


    private lateinit var viewModel: SeasonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_season2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val seasonViewModel = ViewModelProvider(this).get(SeasonViewModel::class.java)


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


    }

}