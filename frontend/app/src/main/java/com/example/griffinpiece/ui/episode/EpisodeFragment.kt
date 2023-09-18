package com.example.griffinpiece.ui.episode

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.griffinpiece.R

class EpisodeFragment : Fragment() {

    private lateinit var viewModel: EpisodeViewModel
    private lateinit var mediaPLayer: MediaPlayer
    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()
    private var pause:Boolean = true

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
        val episodeViewModelFactory = EpisodeViewModelFactory(requireActivity().application, episodeId)
        val episodeViewModel = ViewModelProvider(this, episodeViewModelFactory).get(EpisodeViewModel::class.java)

        var playButton = view.findViewById<Button>(R.id.playBtn)

        playButton.setOnClickListener{
            if(pause == true){
                mediaPLayer.seekTo(mediaPLayer.currentPosition)
                mediaPLayer.start()
                pause = false
                //change the icon of the play button to pause
            }else{
                mediaPLayer.seekTo(mediaPLayer.currentPosition)
                mediaPLayer.pause()
                pause = true
                //change the icon of the play button to play
            }
        }


        episodeViewModel.episodeInfo.observe(viewLifecycleOwner) {
            var uri = it.videoUrl
            mediaPLayer.setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            mediaPLayer.setDataSource(uri)
            mediaPLayer.prepareAsync()
        }



    }
}