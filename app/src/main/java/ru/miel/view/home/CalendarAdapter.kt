package ru.miel.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.miel.R
import ru.miel.databinding.ItemCalendarBinding
import ru.miel.domain.models.Calendar

class CalendarAdapter(
    private val calendarList: MutableList<Calendar>,
    private val onNextClick: (Int) -> Unit,
    private val onBackClick: (Int) -> Unit
) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    @Suppress("DEPRECATION")
    inner class CalendarViewHolder(private val binding: ItemCalendarBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(calendar: Calendar) {
            binding.tvMonthAndYear.text = calendar.monthAndYear

            // Установка дат
            binding.day1.text = calendar.monday
            binding.day2.text = calendar.tuesday
            binding.day3.text = calendar.wednesday
            binding.day4.text = calendar.thursday
            binding.day5.text = calendar.friday
            binding.day6.text = calendar.saturday
            binding.day7.text = calendar.sunday

            binding.day1.setBackgroundResource(if (calendar.isToday) R.color.lime else R.color.white)

            // Обработка кликов на стрелки
            binding.ivArrowRight.setOnClickListener {
                onNextClick(position)
            }
            binding.ivArrowLeft.setOnClickListener {
                onBackClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = ItemCalendarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(calendarList[position])
    }

    override fun getItemCount() = calendarList.size
}
