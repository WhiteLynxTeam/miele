package ru.miel.view.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
import ru.miel.R
import ru.miel.databinding.FragmentHomeBinding
import ru.miel.domain.models.Candidates
import ru.miel.view.activity.MainActivity
import ru.miel.view.showcase.CandidatesAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    private lateinit var candidatesAdapter: CandidatesAdapter

    private val candidates = listOf(
        Candidates(
            R.drawable.img_avatar,
            "Романова Мария Ивановна",
            "22 года",
            "Москва",
            R.drawable.ic_favorites,
            "Введение в профессию риелтор (пройден)",
            "Базовый юридический курс (в процессе)",
            "Курс “Ипотека” (в процессе)",
            "Курс “Налогообложение” (не начат)",
            "Объекты 5",
            "Клиенты 5",
            "Пригласить"
        )
    )

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

        candidatesAdapter = CandidatesAdapter(candidates)
        binding.rcHome.adapter = candidatesAdapter

        binding.tvQuotas.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_showcaseFragment)
        }

        // Показываем или скрываем элементы в зависимости от текущего фрагмента
        (activity as MainActivity).setUIVisibility(showHeader = true, showBottomNav = true)
    }
}