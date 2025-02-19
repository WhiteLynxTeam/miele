package ru.miel.view.office.statistics

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import ru.miel.R
import ru.miel.databinding.FragmentStatisticsBinding
import ru.miel.view.activity.MainActivity
import javax.inject.Inject

class StatisticsFragment : Fragment() {

    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: StatisticsViewModel

    @Inject
    lateinit var vmFactory: StatisticsViewModel.Factory

    private val periods =
        listOf("Месяц", "Год")

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[StatisticsViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.quotes.collect {
                binding.etQuotasIssued.setText(it.quotes.toString())
                binding.etUsedQuotas.setText(it.quotesUsed.toString())
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.countInvitation.collect {
                if (it == 0) binding.etInvited.setText("")
                else binding.etInvited.setText(it.toString())
            }
        }

        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.custom_spinner_item, // Указываем кастомный макет
            periods
        )

        // Применяем адаптер к Spinner
        binding.spinnerPeriod.adapter = spinnerAdapter

        binding.spinnerPeriod.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // parent - это сам Spinner
                // view - View, отображающее выбранный элемент
                // position - позиция выбранного элемента
                // id - идентификатор строки (обычно совпадает с position)

                when (position) {
                    0 -> {
                        viewModel.getQuotes()
                        viewModel.getCountInvitation()
                    }

                    1 -> viewModel.getStatisticQuotes()

                }

//               if (position == 1){
//                   viewModel.getStatisticQuotes()
//               }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Callback вызывается, если ничего не выбрано
                // Можно оставить пустым
            }
        }

        // Показываем или скрываем элементы в зависимости от текущего фрагмента
        (activity as MainActivity).setUIVisibility(
            showHeader = true,
            showBottomNav = true,
        )

        viewModel.getQuotes()
        viewModel.getCountInvitation()
    }
}