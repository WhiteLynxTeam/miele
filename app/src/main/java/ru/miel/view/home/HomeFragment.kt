package ru.miel.view.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import ru.miel.R
import ru.miel.databinding.FragmentHomeBinding
import ru.miel.domain.models.Candidates
import ru.miel.view.activity.MainActivity
import ru.miel.view.showcase.CandidatesAdapter
import ru.miel.view.showcase.CandidatesViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    private val candidatesViewModel: CandidatesViewModel by activityViewModels()
    private lateinit var candidatesAdapter: CandidatesAdapter

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

//        candidatesAdapter = CandidatesAdapter(candidates, this)
//        binding.rcHome.adapter = candidatesAdapter

        candidatesAdapter = CandidatesAdapter(object : CandidatesAdapter.OnIconClickListener {
            override fun onIconClick(position: Int) {
                candidatesViewModel.toggleFavorite(position)
            }

            override fun onButtonClick(position: Int) {
                candidatesViewModel.toggleInvite(position)
            }
        })

        binding.rcHome.adapter = candidatesAdapter
        binding.rcHome.layoutManager = LinearLayoutManager(requireContext())

        // Наблюдение за данными
        candidatesViewModel.candidates.observe(viewLifecycleOwner, Observer { candidates ->
            candidatesAdapter.submitList(candidates)
        })

        //Установка даты в календаре
//        val formattedDate = SimpleDateFormat("E, d MMMM", Locale("ru")).format(Date())
//        val finalDate = formattedDate.split(" ").joinToString(" ") {
//            it.replaceFirstChar { char -> char.uppercase() }
//        }
//        binding.tvMonth.text = finalDate

        // Показываем или скрываем элементы в зависимости от текущего фрагмента
        (activity as MainActivity).setUIVisibility(showHeader = true, showBottomNav = true)
    }
}