package com.example.griffinpiece.ui.episode

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.session.MediaController
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.VideoView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.griffinpiece.R

class EpisodeFragment : Fragment() {

    private lateinit var viewModel: EpisodeViewModel
    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()
    private var pause: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_episode_page, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstaceState: Bundle?) {
        val episodeId = this.requireArguments().get("episodeId") as Int
        val episodeViewModelFactory =
            EpisodeViewModelFactory(requireActivity().application, episodeId)
        val episodeViewModel =
            ViewModelProvider(this, episodeViewModelFactory).get(EpisodeViewModel::class.java)

        var playButton = view.findViewById<Button>(R.id.playBtn)
        var mediaPLayer: MediaPlayer = MediaPlayer()

        var videoView = view.findViewById<VideoView>(R.id.VideoView)


        episodeViewModel.episodeInfo.observe(viewLifecycleOwner) {
            var uri = it.videoUrl
            videoView.setVideoPath("http://www.ebookfrenzy.com/android_book/movie.mp4")
            videoView.start()

            playButton.setOnClickListener {
                if (pause == true) {
                    mediaPLayer.start()
                    pause = false
                    //change the icon of the play button to pause
                } else {
                    mediaPLayer.pause()
                    pause = true
                    //change the icon of the play button to play
                }
            }


        }
    }
}