package com.myapp.mysampelyoutube

import android.os.Bundle
import android.util.Log
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : YouTubeBaseActivity() {

    var playerview :YouTubePlayerView? = null
    var player : YouTubePlayer? = null

    val API_KEY = "AIzaSyDVB7gkcNbRpT8A2TzbdKBgcOihSx1-6w"
    val videoid = "mrAIqeULUL0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPlayer()

        button.setOnClickListener {
            playVideo()
        }
    }

    fun initPlayer(){

        playerview = findViewById(R.id.playerview)
        playerview!!.initialize(API_KEY, object : YouTubePlayer.OnInitializedListener{

            override fun onInitializationSuccess(
                proVider: YouTubePlayer.Provider?,
                youtubePlayer: YouTubePlayer?,
                flag: Boolean
            ) {
              player = youtubePlayer!!
                player!!.setPlayerStateChangeListener(object : YouTubePlayer.PlayerStateChangeListener{
                    override fun onAdStarted() {
                    }

                    override fun onLoading() {
                    }

                    override fun onVideoStarted() {
                    }

                    override fun onLoaded(id: String?) {
                        Log.d("PlayerView", "onLoaded called : " + id)

                        player!!.play()
                    }

                    override fun onVideoEnded() {
                    }

                    override fun onError(p0: YouTubePlayer.ErrorReason?) {
                    }

                })
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {

            }

        })
    }


    fun playVideo(){
        player ?.let{
            if(player!!.isPlaying()) player!!.pause()
        }

        player!!.cueVideo(videoid)
    }
}
