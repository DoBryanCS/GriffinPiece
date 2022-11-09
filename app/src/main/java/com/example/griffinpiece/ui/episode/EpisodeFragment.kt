package com.example.griffinpiece.ui.episode

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.griffinpiece.R

class EpisodeFragment : Fragment() {

    private lateinit var viewModel: EpisodeViewModel
    private lateinit var mediaPLayer: MediaPlayer
    private lateinit var runnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_episode_page, container, false)
    }

//    override fun onViewCreated(view: View, savedInstaceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val episodeViewModel = ViewModelProvider(this).get(EpisodeViewModel::class.java)
//
//
//    }
}