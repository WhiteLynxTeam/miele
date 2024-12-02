package ru.miel.view.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import ru.miel.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //пока для проверки, потом подтянем с БД
        val userName = "Колесникова Мария"
        binding.tvUsersGreeting.text = "Привет, $userName"

        // Установка даты в header
        binding.tvDate.text = SimpleDateFormat("EEEE, d MMMM yyyy 'года'", Locale("ru")).format(Date()).replaceFirstChar { it.uppercase() }

    }

    // Метод для управления видимостью элементов в activity_main
    fun setUIVisibility(showHeader: Boolean, showBottomNav: Boolean) {
        binding.header.visibility = if (showHeader) View.VISIBLE else View.GONE
        binding.bottomNavigation.visibility = if (showBottomNav) View.VISIBLE else View.GONE
    }
}