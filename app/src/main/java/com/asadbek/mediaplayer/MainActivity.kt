package com.asadbek.mediaplayer

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import android.widget.Toast
import com.asadbek.mediaplayer.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mediaPlayer: MediaPlayer // media playerdan
    lateinit var handler: Handler // handler
    val httpMedia = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-9.mp3"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mediaPlayer = MediaPlayer()
        // agarda internet orqali musiqa olmoqchi bo`lsak internet uchun permission berishimiz kerak
        // if you use internet, don't forget permission internet
        // duration - umumiy daqiqasi
        // currentposition - joriy ketyotgan o`rni
        // mediaPlayer = MediaPlayer.create(this.R.raw.relax) // media playerni tanitib olish
        mediaPlayer.setDataSource(httpMedia)
        mediaPlayer.setOnPreparedListener { // internet orqali eshitilayotgandagina ushbu onPreparedListerning ichiga joylashtiriladi!
            binding.seekBar.max =
                mediaPlayer.duration // seek bar davomiyligini mediapalyerdagi musiqaning uzunligiga tenglaymiz
            mediaPlayer.start()
            handler = Handler(Looper.getMainLooper())
            handler.postDelayed(runnable, 1000) // delayMills - o`zgarish oralig`i xar bir sekund


        }
        mediaPlayer.prepareAsync()
        binding.btnPlay.setOnClickListener {
            mediaPlayer.start()
            Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show()
        }
        binding.btnPause.setOnClickListener {
            mediaPlayer.pause()
            Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show()
        }
        binding.btnStop.setOnClickListener {
            mediaPlayer.stop()
            Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show()
        }
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) { // User tomonidan ozgartirilsagina ishlaydi "true" bo`lganda
                    mediaPlayer.seekTo(progress)
                }

            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) { // boshlangandan keyin

            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) { // seek bar qoyib yuborilgandan keyin ishlovchi xolati

            }
        })

    }

    val runnable = object : Runnable {
        override fun run() {
            binding.seekBar.progress = mediaPlayer.currentPosition
            handler.postDelayed(this, 1000)
        }
    }

}




