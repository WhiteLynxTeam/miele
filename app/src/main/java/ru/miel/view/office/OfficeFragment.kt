package ru.miel.view.office

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
import ru.miel.R
import ru.miel.databinding.FragmentOfficeBinding
import ru.miel.view.activity.MainActivity

class OfficeFragment : Fragment() {

    private var _binding: FragmentOfficeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: OfficeViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOfficeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val activity = requireActivity() as MainActivity
        val btRequestQuotas = activity.findViewById<AppCompatButton>(R.id.btn_request_quotas)

        // Изменение положения кнопки
        val params = btRequestQuotas.layoutParams as ConstraintLayout.LayoutParams
        params.apply {
            //переместить кнопку
            topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            bottomToBottom = ConstraintLayout.LayoutParams.UNSET
            topMargin = 89.dpToPx()
        }
        btRequestQuotas.layoutParams = params
    }

    // Утилита для dp (добавьте в отдельный файл)
    fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvOffice.setOnClickListener {
            findNavController().navigate(R.id.action_officeFragment_to_addressFragment)
        }
        binding.tvAdministrator.setOnClickListener {
            findNavController().navigate(R.id.action_officeFragment_to_administratorFragment)
        }
        binding.tvStatistics.setOnClickListener {
            findNavController().navigate(R.id.action_officeFragment_to_statisticsFragment)
        }

        // Показываем или скрываем элементы в зависимости от текущего фрагмента
        (activity as MainActivity).setUIVisibility(
            showHeader = true,
            showBottomNav = true,
            showRequestQuotas = true
        )
    }
}