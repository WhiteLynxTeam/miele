package ru.miel.view.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.miel.R
import ru.miel.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val navController by lazy {
        NavHostFragment.findNavController(supportFragmentManager.findFragmentById(R.id.fragment_placeholder) as NavHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setupWithNavController(navController)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.showcase -> {
                    navController.navigate(R.id.showcaseFragment)
                    true
                }
                R.id.message -> {
                    navController.navigate(R.id.chatFragment)
                    true
                }
                R.id.office -> {
                    navController.navigate(R.id.officeFragment)
                    true
                }
                R.id.favorites -> {
                    navController.navigate(R.id.favoritesFragment)
                    true
                }
                else -> false
            }
        }

        //пока для проверки, потом подтянем с БД
        val userName = "Колесникова Мария"
        binding.tvUsersGreeting.text = "Привет, $userName"

        // Установка даты в header
        binding.tvDate.text = SimpleDateFormat("EEEE, d MMMM yyyy 'года'", Locale("ru")).format(Date()).replaceFirstChar { it.uppercase() }

    }

//    //1. Проверка на существование фрагмента. Если фрагмент есть, то мы его не пересоздаем, а используем вновь:
//    private fun checkFragmentExistence(tag: String): Fragment? =
//        supportFragmentManager.findFragmentByTag(tag)
//
//    //2. Сам запуск фрагмента:
//    private fun changeFragment(fragment: Fragment, tag: String) {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fragment_placeholder, fragment, tag)
//            .addToBackStack(null)
//            .commit()
//    }

    // Метод для управления видимостью элементов в activity_main
    fun setUIVisibility(showHeader: Boolean, showBottomNav: Boolean) {
        binding.header.visibility = if (showHeader) View.VISIBLE else View.GONE
        binding.bottomNavigation.visibility = if (showBottomNav) View.VISIBLE else View.GONE
    }
}