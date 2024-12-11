package ru.miel.view.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import dagger.android.support.AndroidSupportInjection
import ru.miel.databinding.FragmentHomeBinding
import ru.miel.domain.models.DayOfWeek
import ru.miel.view.activity.MainActivity
import ru.miel.view.showcase.CandidatesAdapter
import ru.miel.view.showcase.CandidatesViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    private val candidatesViewModel: CandidatesViewModel by activityViewModels()

    private val candidatesAdapter by lazy {
        CandidatesAdapter({ pos ->
            println("ShowcaseFragment onIconClick = $pos")
        },
            { pos ->
                println("ShowcaseFragment onButtonClick = $pos")
            })
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Начальная дата (текущая неделя)
        val today = Calendar.getInstance()
        val weeks = generateWeeks(today)

        val calendarAdapter = CalendarAdapter(
            weeks,
            onNextClick = { _ ->
                // Анимация сдвига вправо
                binding.rcCalendar.animate()
                    .translationX(-1000f) // Сдвиг вправо за пределы экрана
                    .setDuration(100)
                    .withEndAction {
                        today.add(Calendar.WEEK_OF_YEAR, 1)
                        updateWeeks(weeks, today)
                        binding.rcCalendar.translationX = 1000f // Перемещение влево за пределы экрана
                        binding.rcCalendar.animate()
                            .translationX(0f) // Возвращение в центр экрана
                            .setDuration(100)
                            .start()
                    }
                    .start()
            },
            onBackClick = { _ ->
                // Анимация сдвига влево
                binding.rcCalendar.animate()
                    .translationX(1000f) // Сдвиг влево за пределы экрана
                    .setDuration(100)
                    .withEndAction {
                        today.add(Calendar.WEEK_OF_YEAR, -1)
                        updateWeeks(weeks, today)
                        binding.rcCalendar.translationX = -1000f // Перемещение вправо за пределы экрана
                        binding.rcCalendar.animate()
                            .translationX(0f) // Возвращение в центр экрана
                            .setDuration(100)
                            .start()
                    }
                    .start()
            }
        )


        binding.rcCalendar.adapter = calendarAdapter

        binding.rcHome.adapter = candidatesAdapter

        // Наблюдение за данными
        candidatesViewModel.candidates.observe(viewLifecycleOwner, Observer { candidates ->
            candidatesAdapter.submitList(candidates)
        })

        settingTheDate()

        // Показываем или скрываем элементы в зависимости от текущего фрагмента
        (activity as MainActivity).setUIVisibility(showHeader = true, showBottomNav = true)
    }

    //Установка даты в календаре
    private fun settingTheDate() {
        val formattedDate = SimpleDateFormat("E, d MMMM", Locale("ru")).format(Date())
        val finalDate = formattedDate.split(" ").joinToString(" ") {
            it.replaceFirstChar { char -> char.uppercase() }
        }
        binding.tvMonth.text = finalDate
    }

    private fun generateWeeks(calendar: java.util.Calendar): MutableList<DayOfWeek> {
        val weeks = mutableListOf<DayOfWeek>()
        for (i in 0 until 1) { // Начальный список (одна неделя)
            weeks.add(createWeek(calendar))
        }
        return weeks
    }

    private fun createWeek(calendar: java.util.Calendar): DayOfWeek {
        val sdf = SimpleDateFormat("dd", Locale.getDefault())
        val weekFormat = SimpleDateFormat("MMMM yyyy", Locale("ru"))

        val monday = calendar.apply { set(java.util.Calendar.DAY_OF_WEEK, java.util.Calendar.MONDAY) }
        return DayOfWeek(
            monday = sdf.format(monday.time),
            tuesday = sdf.format(monday.apply { add(java.util.Calendar.DAY_OF_MONTH, 1) }.time),
            wednesday = sdf.format(monday.apply { add(java.util.Calendar.DAY_OF_MONTH, 1) }.time),
            thursday = sdf.format(monday.apply { add(java.util.Calendar.DAY_OF_MONTH, 1) }.time),
            friday = sdf.format(monday.apply { add(java.util.Calendar.DAY_OF_MONTH, 1) }.time),
            saturday = sdf.format(monday.apply { add(java.util.Calendar.DAY_OF_MONTH, 1) }.time),
            sunday = sdf.format(monday.apply { add(java.util.Calendar.DAY_OF_MONTH, 1) }.time),
            monthAndYear = weekFormat.format(monday.time)
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateWeeks(weeks: MutableList<DayOfWeek>, calendar: java.util.Calendar) {
        weeks.clear()
        weeks.add(createWeek(calendar))
        binding.rcCalendar.adapter?.notifyDataSetChanged()
    }
}