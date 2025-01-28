package ru.miel.view.showcase

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import ru.miel.databinding.FragmentShowcaseBinding
import ru.miel.utils.replaceAfterLastSpace
import ru.miel.view.activity.ActivityMainViewModel
import ru.miel.view.activity.MainActivity
import javax.inject.Inject

class ShowcaseFragment : Fragment() {

    private var _binding: FragmentShowcaseBinding? = null
    private val binding get() = _binding!!

    private val activityViewModel: ActivityMainViewModel by activityViewModels()
    private lateinit var viewModel: ShowcaseViewModel

    @Inject
    lateinit var vmFactory: ShowcaseViewModel.Factory

    private val candidatesAdapter by lazy {
        CandidatesAdapter({ id, flag ->
            viewModel.toggleFavorite(id, flag)
        },
            { id, flag ->
                viewModel.toggleInvite(id, flag)
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

        _binding = FragmentShowcaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[ShowcaseViewModel::class.java]

        binding.rcCandidates.adapter = candidatesAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isInvite.collect {
                if (it.first) {
                    candidatesAdapter.updateInvite(it.second, it.third)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isFavorite.collect {
                if (it.first) {
                    candidatesAdapter.updateFavorite(it.second, it.third)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.candidates.collect {
                binding.tvCandidates.text =
                    binding.tvCandidates.text.toString().trim().replaceAfterLastSpace(it.size)
                candidatesAdapter.setData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.quotes.collect {
                binding.tvQuotas.text =
                    binding.tvQuotas.text.toString().trim().replaceAfterLastSpace(it)
            }
        }

        // Показываем или скрываем элементы в зависимости от текущего фрагмента
        (activity as MainActivity).setUIVisibility(showHeader = true, showBottomNav = true)

        activityViewModel.getFullNameUser()
        activityViewModel.getPhotoUser()
        viewModel.getCandidates()
    }

}