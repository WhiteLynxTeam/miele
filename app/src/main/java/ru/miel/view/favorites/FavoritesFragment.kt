package ru.miel.view.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.collection.mutableIntSetOf
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import ru.miel.databinding.FragmentFavoritesBinding
import ru.miel.domain.models.Candidates
import ru.miel.view.activity.MainActivity
import ru.miel.view.showcase.CandidatesAdapter

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FavoritesViewModel

    private lateinit var candidatesAdapter: CandidatesAdapter

    private val candidates = mutableIntSetOf()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        candidatesAdapter = CandidatesAdapter(candidates)
//        binding.rcFavorites.adapter = candidatesAdapter

        // Показываем или скрываем элементы в зависимости от текущего фрагмента
        (activity as MainActivity).setUIVisibility(showHeader = true, showBottomNav = true)
    }
}