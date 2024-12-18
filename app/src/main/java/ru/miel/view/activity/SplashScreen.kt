package ru.miel.view.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import ru.miel.R
import ru.miel.databinding.ActivitySplashBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Установка пути к видеофайлу
        val videoPath = "android.resource://$packageName/${R.raw.splash}"
        val uri = Uri.parse(videoPath)

        binding.video.setVideoURI(uri)

        // Установка слушателя завершения видео
        binding.video.setOnCompletionListener {
            // Переход на другую активность
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
        }

        // Начать воспроизведение
        binding.video.start()

    }
}