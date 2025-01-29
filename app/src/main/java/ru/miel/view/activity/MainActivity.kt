package ru.miel.view.activity

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import dagger.android.AndroidInjection
import kotlinx.coroutines.launch
import ru.miel.R
import ru.miel.databinding.ActivityMainBinding
import ru.miel.domain.BASE_URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: ActivityMainViewModel

    @Inject
    lateinit var vmFactory: ActivityMainViewModel.Factory

    private val navController by lazy {
        NavHostFragment.findNavController(supportFragmentManager.findFragmentById(R.id.fragment_placeholder) as NavHostFragment)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, vmFactory)[ActivityMainViewModel::class.java]

        binding.bottomNavigation.setupWithNavController(navController)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                /*R.id.home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }*/

                R.id.showcase -> {
                    navController.navigate(R.id.showcaseFragment)
                    true
                }

                R.id.quotas -> {
                    navController.navigate(R.id.statisticsFragment)
                    true
                }

                R.id.favorites -> {
                    navController.navigate(R.id.favoritesFragment)
                    true
                }

                R.id.invitations -> {
                    navController.navigate(R.id.invitationsFragment)
                    true
                }

                else -> false
            }
        }

        lifecycleScope.launch {
            viewModel.fullName.collect {
                binding.tvUsersGreeting.text = "Привет, $it"
                val words = it.trim().split("\\s+".toRegex()) // Разбиваем по пробелам
                binding.tvAvatar.text = "${words[0].first().uppercase()}${words[1].first().uppercase()}"
            }
        }

        lifecycleScope.launch {
            viewModel.photo.collect {
                if (it.isNullOrEmpty()) binding.tvAvatar.visibility = View.VISIBLE
                else {
                    // Загружаем фото с сервера
                    Glide.with(this@MainActivity)
                        .load(it)
                        .circleCrop()
                        .into(binding.ivAvatar)
                }
            }
        }

        //пока для проверки, потом подтянем с БД
//        val userName = "Колесникова Мария"
//        binding.tvUsersGreeting.text = "Привет, $userName"

        // Установка даты в header
        binding.tvDate.text =
            SimpleDateFormat("EEEE, d MMMM yyyy 'года'", Locale("ru")).format(Date())
                .replaceFirstChar { it.uppercase() }
    }

    // Метод для управления видимостью элементов в activity_main
    fun setUIVisibility(showHeader: Boolean, showBottomNav: Boolean) {
        binding.header.visibility = if (showHeader) View.VISIBLE else View.GONE
        binding.bottomNavigation.visibility = if (showBottomNav) View.VISIBLE else View.GONE
    }
}