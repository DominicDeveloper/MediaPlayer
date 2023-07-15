package com.asadbek.mediaplayer

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asadbek.mediaplayer.databinding.ActivityMain2Binding
import java.io.IOException

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    var mediaPlayer:MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        mediaPlayer = MediaPlayer()

        binding.btnPlay2.setOnClickListener {
            playMusic()
        }
        binding.btnPause2.setOnClickListener {
            pauseMusic()
        }


    }

    fun playMusic(){
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this,R.raw.tm)
            mediaPlayer!!.isLooping = true
            mediaPlayer!!.start()

        }else mediaPlayer!!.start()
    }
    fun pauseMusic(){
        if (mediaPlayer?.isPlaying == true) mediaPlayer?.pause()
    }
    fun stopMusic(){
        if (mediaPlayer != null){
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }
    fun playContentUri(uri: Uri){
        val mediaPlayer = MediaPlayer().apply {
            setDataSource(application,uri)
            setAudioAttributes(
                AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(
                    AudioAttributes.CONTENT_TYPE_MUSIC).build())
            prepare()
            start()
        }
    }
    // bazi xatolik handler orqali uchraganda
    fun playContentUriWithTryCatch(uri: Uri){
        var mMediaPlayer:MediaPlayer? = null
        try {
            val mediaPlayer = MediaPlayer().apply {
                setDataSource(application,uri)
                setAudioAttributes(
                    AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(
                        AudioAttributes.CONTENT_TYPE_MUSIC).build())
                prepare()
                start()
            }
        }catch (exception: IOException){
            mMediaPlayer?.release()
            mMediaPlayer = null
        }
    }
    // dastur yopilganda mediplayerni yopish
    override fun onStop() {
        super.onStop()
        if (mediaPlayer != null){
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}