package ru.miel.view.showcase

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import ru.miel.R
import ru.miel.databinding.FragmentShowcaseBinding
import ru.miel.domain.models.Candidates
import ru.miel.view.activity.MainActivity

class ShowcaseFragment : Fragment() {

    private var _binding: FragmentShowcaseBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ShowcaseViewModel

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
        ),
        Candidates(
            R.drawable.img_avatar,
            "Романова Мария Ивановна",
            "22 года",
            "Москва",
            R.drawable.ic_favorites_candidates_selected,
            "Введение в профессию риелтор (пройден)",
            "Базовый юридический курс (в процессе)",
            "Курс “Ипотека” (в процессе)",
            "Курс “Налогообложение” (не начат)",
            "Объекты 5",
            "Клиенты 5",
            "Приглашен"
        ),
        Candidates(
            R.drawable.img_avatar,
            "Романова Мария Ивановна",
            "22 года",
            "Москва",
            R.drawable.ic_favorites_candidates_selected,
            "Введение в профессию риелтор (пройден)",
            "Базовый юридический курс (в процессе)",
            "Курс “Ипотека” (в процессе)",
            "Курс “Налогообложение” (не начат)",
            "Объекты 5",
            "Клиенты 5",
            "Пригласить"
        ),
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

        candidatesAdapter = CandidatesAdapter(candidates)
        binding.rcCandidates.adapter = candidatesAdapter

        // Показываем или скрываем элементы в зависимости от текущего фрагмента
        (activity as MainActivity).setUIVisibility(showHeader = true, showBottomNav = true)
    }
}