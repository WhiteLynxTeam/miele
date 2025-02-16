package ru.miel.view.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import ru.miel.R
import ru.miel.databinding.FragmentFilterBinding

class FilterFragment : DialogFragment() {

    private lateinit var binding: FragmentFilterBinding

    private var listener: FilterListener? = null

    private val onTimeClickListener = View.OnClickListener { view ->
        val currentBackground = view.background
        val selectedDrawable = ContextCompat.getDrawable(view.context, R.drawable.background_filters_selected)
        val defaultDrawable = ContextCompat.getDrawable(view.context, R.drawable.background_input)//можно удалить

        if (currentBackground.constantState == selectedDrawable?.constantState) {
            // Если текущий drawable равен выбранному, возвращаем исходный
            view.setBackgroundResource(R.drawable.background_input)
        } else {
            // Иначе устанавливаем выбранный drawable
            view.setBackgroundResource(R.drawable.background_filters_selected)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilterBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //устанавливаем размеры для диалогового окна
        dialog?.window?.setLayout(
            (resources.displayMetrics.widthPixels * 1.0).toInt(),
            (resources.displayMetrics.heightPixels * 1.0).toInt(),
        )

        binding.tvCancel.setOnClickListener {
            dismiss()
        }

        //при нажатии красим bg textview при повторном нажатии возвращаем исходный bg
        with(binding) {
            tvCoursesRealtor.setOnClickListener(onTimeClickListener)
            tvCoursesMortgage.setOnClickListener(onTimeClickListener)
            tvCoursesLegal.setOnClickListener(onTimeClickListener)
            tvCoursesTaxation.setOnClickListener(onTimeClickListener)
        }

        //по нжатию применить, подтверждаем выбранные фильтры
        binding.btnApply.setOnClickListener {
            //Получение возраста "от" и "до"
            binding.etAgeFrom.text.toString().toIntOrNull() ?: 0  //если ни чего не выбрано от будет ставиться "0"
            binding.etAgeTo.text.toString().toIntOrNull() //?: Int.MAX_VALUE //если ни чего не выбрано то будет макс значене Int

            //Получение выбранные курсов
            val selectedCourses = mutableListOf<String>()
            if (binding.tvCoursesRealtor.isSelected) selectedCourses.add("Введение в профессию риелтор")
            if (binding.tvCoursesMortgage.isSelected) selectedCourses.add("Ипотека")
            if (binding.tvCoursesLegal.isSelected) selectedCourses.add("Базовый юридический курс")
            if (binding.tvCoursesTaxation.isSelected) selectedCourses.add("Налогообложение")

            listener?.onFilterApplied(binding.etAgeFrom, binding.etAgeTo, selectedCourses)
            dismiss()
        }
        return

    }

    fun setFilterListener(listener: FilterListener) {
        this.listener = listener
    }
}