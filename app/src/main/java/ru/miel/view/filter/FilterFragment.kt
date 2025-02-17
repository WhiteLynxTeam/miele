package ru.miel.view.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import ru.miel.R
import ru.miel.databinding.FragmentFilterBinding
import ru.miel.domain.models.CandidatesFilter
import ru.miel.view.activity.ActivityMainViewModel

class FilterFragment : DialogFragment() {

    private lateinit var binding: FragmentFilterBinding

    private val activityViewModel: ActivityMainViewModel by activityViewModels()

    private val onTimeClickListener = View.OnClickListener { view ->
        val currentBackground = view.background
        val selectedDrawable =
            ContextCompat.getDrawable(view.context, R.drawable.background_filters_selected)
        val defaultDrawable =
            ContextCompat.getDrawable(view.context, R.drawable.background_input)//можно удалить

        if (currentBackground.constantState == selectedDrawable?.constantState) {
            // Если текущий drawable равен выбранному, возвращаем исходный
            view.setBackgroundResource(R.drawable.background_input)
            view.isSelected = false
        } else {
            // Иначе устанавливаем выбранный drawable
            view.setBackgroundResource(R.drawable.background_filters_selected)
            view.isSelected = true
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

        initFields(activityViewModel.getFilter())

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

            if (isEmptyField()) {
                activityViewModel.setFilter(null)
            }

            val minAge = binding.etAgeFrom.text.toString().toIntOrNull()
            val maxAge = binding.etAgeTo.text.toString().toIntOrNull()
            if (minAge == null && maxAge != null) {
                binding.etAgeFrom.error = "Введите мин. возраст."
                binding.etAgeFrom.requestFocus()
                return@setOnClickListener
            } else {
                activityViewModel.setFilter(
                    CandidatesFilter(
                        age_min = minAge,
                        age_max = maxAge,
                        course_rieltor_join = binding.tvCoursesRealtor.isSelected,
                        basic_legal_course = binding.tvCoursesLegal.isSelected,
                        course_mortgage = binding.tvCoursesMortgage.isSelected,
                        course_taxation = binding.tvCoursesTaxation.isSelected,
                    )
                )
            }

            dismiss()
        }
    }

    private fun initFields(filter: CandidatesFilter?) {
        if (filter != null) {
            if (filter.age_min != null) binding.etAgeFrom.setText(filter.age_min.toString())
            if (filter.age_max != null) binding.etAgeTo.setText(filter.age_max.toString())
            if (filter.course_rieltor_join) {
                binding.tvCoursesRealtor.isSelected = true
                binding.tvCoursesRealtor.setBackgroundResource(R.drawable.background_filters_selected)
            } else {
                binding.tvCoursesRealtor.isSelected = false
                binding.tvCoursesRealtor.setBackgroundResource(R.drawable.background_input)
            }
            if (filter.basic_legal_course) {
                binding.tvCoursesLegal.isSelected = true
                binding.tvCoursesLegal.setBackgroundResource(R.drawable.background_filters_selected)
            } else {
                binding.tvCoursesLegal.isSelected = false
                binding.tvCoursesLegal.setBackgroundResource(R.drawable.background_input)
            }
            if (filter.course_mortgage) {
                binding.tvCoursesMortgage.isSelected = true
                binding.tvCoursesMortgage.setBackgroundResource(R.drawable.background_filters_selected)
            } else {
                binding.tvCoursesMortgage.isSelected = false
                binding.tvCoursesMortgage.setBackgroundResource(R.drawable.background_input)
            }
            if (filter.course_taxation) {
                binding.tvCoursesTaxation.isSelected = true
                binding.tvCoursesTaxation.setBackgroundResource(R.drawable.background_filters_selected)
            } else {
                binding.tvCoursesTaxation.isSelected = false
                binding.tvCoursesTaxation.setBackgroundResource(R.drawable.background_input)
            }
        }
    }

    private fun isEmptyField(): Boolean {
        return binding.etAgeFrom.text.toString().trim().isEmpty()
                && binding.etAgeTo.text.toString().trim().isEmpty()
                && !binding.tvCoursesRealtor.isSelected
                && !binding.tvCoursesMortgage.isSelected
                && !binding.tvCoursesLegal.isSelected
                && !binding.tvCoursesTaxation.isSelected
    }
}