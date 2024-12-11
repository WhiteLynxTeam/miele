package ru.miel.view.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.miel.R
import ru.miel.databinding.ItemCalendarBinding
import ru.miel.domain.models.DayOfWeek
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CalendarAdapter(
    private val dayOfWeekList: MutableList<DayOfWeek>,
    private val onNextClick: (Int) -> Unit,
    private val onBackClick: (Int) -> Unit
) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    private var selectedPosition: Int? = null // Хранение позиции выбранной недели
    private var selectedDay: Int? = null // Хранение выбранного дня недели (1 - понедельник, 2 - вторник и т.д.)

    private val today = Calendar.getInstance() // Текущая дата

    @Suppress("DEPRECATION")
    inner class CalendarViewHolder(private val binding: ItemCalendarBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dayOfWeek: DayOfWeek, position: Int) {
            binding.tvMonthAndYear.text = getMonthAndYearInNominative(
                SimpleDateFormat("MMMM yyyy", Locale("ru")).parse(dayOfWeek.monthAndYear)!!
            )

            // Установка дат
            binding.day1.text = dayOfWeek.monday
            binding.day2.text = dayOfWeek.tuesday
            binding.day3.text = dayOfWeek.wednesday
            binding.day4.text = dayOfWeek.thursday
            binding.day5.text = dayOfWeek.friday
            binding.day6.text = dayOfWeek.saturday
            binding.day7.text = dayOfWeek.sunday

            // Определяем текущую дату для подсветки
            val currentDay = today.get(Calendar.DAY_OF_MONTH).toString()
            val currentMonth = today.get(Calendar.MONTH)
            val currentYear = today.get(Calendar.YEAR)

            val weekCalendar = Calendar.getInstance().apply {
                time = SimpleDateFormat("MMMM yyyy", Locale("ru")).parse(dayOfWeek.monthAndYear)!!
            }
            val isSameMonthAndYear = currentMonth == weekCalendar.get(Calendar.MONTH) &&
                    currentYear == weekCalendar.get(Calendar.YEAR)

            // Сбрасываем фон для всех дней
            resetDayBackgrounds()

            // Подсвечиваем текущий день, если он есть в неделе и это текущий месяц и год
            if (isSameMonthAndYear) {
                if (dayOfWeek.monday == currentDay) binding.day1.setBackgroundResource(R.color.selected_day_calendar)
                if (dayOfWeek.tuesday == currentDay) binding.day2.setBackgroundResource(R.color.selected_day_calendar)
                if (dayOfWeek.wednesday == currentDay) binding.day3.setBackgroundResource(R.color.selected_day_calendar)
                if (dayOfWeek.thursday == currentDay) binding.day4.setBackgroundResource(R.color.selected_day_calendar)
                if (dayOfWeek.friday == currentDay) binding.day5.setBackgroundResource(R.color.selected_day_calendar)
                if (dayOfWeek.saturday == currentDay) binding.day6.setBackgroundResource(R.color.selected_day_calendar)
                if (dayOfWeek.sunday == currentDay) binding.day7.setBackgroundResource(R.color.selected_day_calendar)
            }

            // Изменяем фон выбранного дня
            if (selectedPosition == position) {
                when (selectedDay) {
                    1 -> binding.day1.setBackgroundResource(R.color.lime)
                    2 -> binding.day2.setBackgroundResource(R.color.lime)
                    3 -> binding.day3.setBackgroundResource(R.color.lime)
                    4 -> binding.day4.setBackgroundResource(R.color.lime)
                    5 -> binding.day5.setBackgroundResource(R.color.lime)
                    6 -> binding.day6.setBackgroundResource(R.color.lime)
                    7 -> binding.day7.setBackgroundResource(R.color.lime)
                }
            }

            // Обработка кликов на стрелки
            binding.ivArrowRight.setOnClickListener {
                resetSelection() // Сброс выделения
                onNextClick(position)
            }
            binding.ivArrowLeft.setOnClickListener {
                resetSelection() // Сброс выделения
                onBackClick(position)
            }

            // Обработка кликов на день недели
            binding.day1.setOnClickListener { selectDate(position, 1) }
            binding.day2.setOnClickListener { selectDate(position, 2) }
            binding.day3.setOnClickListener { selectDate(position, 3) }
            binding.day4.setOnClickListener { selectDate(position, 4) }
            binding.day5.setOnClickListener { selectDate(position, 5) }
            binding.day6.setOnClickListener { selectDate(position, 6) }
            binding.day7.setOnClickListener { selectDate(position, 7) }
        }

        private fun resetDayBackgrounds() {
            binding.day1.setBackgroundResource(R.color.white)
            binding.day2.setBackgroundResource(R.color.white)
            binding.day3.setBackgroundResource(R.color.white)
            binding.day4.setBackgroundResource(R.color.white)
            binding.day5.setBackgroundResource(R.color.white)
            binding.day6.setBackgroundResource(R.color.white)
            binding.day7.setBackgroundResource(R.color.white)
        }

        // Функция выбора дня
        private fun selectDate(position: Int, day: Int) {
            selectedPosition = position
            selectedDay = day
            notifyDataSetChanged() // Обновляем все элементы
        }

        // Сброс выбора
        private fun resetSelection() {
            selectedPosition = null
            selectedDay = null
        }

        private fun getMonthAndYearInNominative(date: Date): String {
            val monthsNominative = arrayOf(
                "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
            )

            val dateFormatSymbol = DateFormatSymbols(Locale("ru")).apply {
                months = monthsNominative
            }

            val dateFormat = SimpleDateFormat("MMMM yyyy", Locale("ru")).apply {
                dateFormatSymbols = dateFormatSymbol
            }

            return dateFormat.format(date)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = ItemCalendarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(dayOfWeekList[position], position)
    }

    override fun getItemCount() = dayOfWeekList.size
}
