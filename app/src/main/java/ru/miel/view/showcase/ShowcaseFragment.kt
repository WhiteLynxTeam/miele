package ru.miel.view.showcase

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
import ru.miel.databinding.FragmentShowcaseBinding
import ru.miel.domain.models.Candidates
import ru.miel.view.activity.MainActivity

class ShowcaseFragment : Fragment() {

    private var _binding: FragmentShowcaseBinding? = null
    private val binding get() = _binding!!

    private val candidatesViewModel: CandidatesViewModel by activityViewModels()
    private lateinit var candidatesAdapter: CandidatesAdapter

    private lateinit var viewModel: ShowcaseViewModel

    private val candidates = mutableListOf(
        Candidates(
            R.drawable.img_avatar,
            "Романова Мария Ивановна",
            "22 года",
            "Москва",
            false,
            "Введение в профессию риелтор (пройден)",
            "Базовый юридический курс (в процессе)",
            "Курс “Ипотека” (в процессе)",
            "Курс “Налогообложение” (не начат)",
            "Объекты 5",
            "Клиенты 5",
            "Пригласить",
            false
        ),
        Candidates(
            R.drawable.img_avatar,
            "Романова Мария Ивановна",
            "22 года",
            "Москва",
            false,
            "Введение в профессию риелтор (пройден)",
            "Базовый юридический курс (в процессе)",
            "Курс “Ипотека” (в процессе)",
            "Курс “Налогообложение” (не начат)",
            "Объекты 5",
            "Клиенты 5",
            "Приглашен",
            false
        ),
        Candidates(
            R.drawable.img_avatar,
            "Романова Мария Ивановна",
            "22 года",
            "Москва",
            false,
            "Введение в профессию риелтор (пройден)",
            "Базовый юридический курс (в процессе)",
            "Курс “Ипотека” (в процессе)",
            "Курс “Налогообложение” (не начат)",
            "Объекты 5",
            "Клиенты 5",
            "Пригласить",
            false
        ),
        Candidates(
            R.drawable.img_avatar,
            "Романова Мария Ивановна",
            "22 года",
            "Москва",
            false,
            "Введение в профессию риелтор (пройден)",
            "Базовый юридический курс (в процессе)",
            "Курс “Ипотека” (в процессе)",
            "Курс “Налогообложение” (не начат)",
            "Объекты 5",
            "Клиенты 5",
            "Пригласить",
            false
        ),
    )

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentShowcaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        candidatesAdapter = CandidatesAdapter(candidates, this)
//        binding.rcCandidates.adapter = candidatesAdapter

        candidatesAdapter = CandidatesAdapter(object : CandidatesAdapter.OnIconClickListener {
            override fun onIconClick(position: Int) {
                candidatesViewModel.toggleFavorite(position)
            }

            override fun onButtonClick(position: Int) {
                candidatesViewModel.toggleInvite(position)
            }
        })

        binding.rcCandidates.adapter = candidatesAdapter
        binding.rcCandidates.layoutManager = LinearLayoutManager(requireContext())

        // Наблюдение за данными
        candidatesViewModel.candidates.observe(viewLifecycleOwner, Observer { candidates ->
            candidatesAdapter.submitList(candidates)
        })

        // Показываем или скрываем элементы в зависимости от текущего фрагмента
        (activity as MainActivity).setUIVisibility(showHeader = true, showBottomNav = true)
    }

}