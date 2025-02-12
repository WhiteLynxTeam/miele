package ru.miel.view.favorites

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import ru.miel.R
import ru.miel.databinding.FragmentFavoritesBinding
import ru.miel.domain.models.enummodel.SortOption
import ru.miel.utils.showRadioDialog
import ru.miel.view.activity.MainActivity
import ru.miel.view.showcase.CandidatesAdapter
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FavoritesViewModel

    @Inject
    lateinit var vmFactory: FavoritesViewModel.Factory

    private val candidatesAdapter by lazy {
        CandidatesAdapter({ id, flag, idFavorite ->
//            viewModel.toggleFavorite(id, flag)
            println("FavoritesFragment onIconClick = $id")
        },
            { id ->
                viewModel.toggleInvite(id)
            })
    }

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

        viewModel =
            ViewModelProvider(this, vmFactory)[FavoritesViewModel::class.java]

        binding.rcFavorites.adapter = candidatesAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.candidates.collect {
                candidatesAdapter.setData(it)
                candidatesAdapter.sort()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isInvite.collect {
                if (it.first) {
                    candidatesAdapter.updateInvite(it.second)
                }
            }
        }

        // Показываем или скрываем элементы в зависимости от текущего фрагмента
        (activity as MainActivity).setUIVisibility(
            showHeader = true,
            showBottomNav = true,
        )

        binding.ivSorting.setOnClickListener {
            showRadioDialog(requireContext()) {
                candidatesAdapter.sort()
            }
        }

        viewModel.getFavorites()

        binding.borderFilter.setOnClickListener {
            findNavController().navigate(R.id.action_favoritesFragment_to_filterFragment)
        }
    }
}