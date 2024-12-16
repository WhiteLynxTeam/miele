package ru.miel.view.showcase

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import ru.miel.databinding.FragmentShowcaseBinding
import ru.miel.view.activity.MainActivity
import javax.inject.Inject

class ShowcaseFragment : Fragment() {

    private var _binding: FragmentShowcaseBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ShowcaseViewModel

    @Inject
    lateinit var vmFactory: ShowcaseViewModel.Factory

    private val candidatesAdapter by lazy {
        CandidatesAdapter({ pos ->
            viewModel.toggleFavorite(pos)
            //println("ShowcaseFragment onIconClick = $pos")
        },
            { pos ->
                viewModel.toggleInvite(pos)
                //println("ShowcaseFragment onButtonClick = $pos")
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[ShowcaseViewModel::class.java]

        binding.rcCandidates.adapter = candidatesAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isEntry.collect {
                if (it) viewModel.getCandidates()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.candidates.collect {
                candidatesAdapter.setData(it)
//                candidatesAdapter.submitList(it)
            }
        }


//        candidatesAdapter = CandidatesAdapter(object : CandidatesAdapter.OnIconClickListener {
//            override fun onIconClick(position: Int) {
//                viewModel.toggleFavorite(position)
//            }
//
//            override fun onButtonClick(position: Int) {
//                viewModel.toggleInvite(position)
//            }
//        })

//
//        // Наблюдение за данными
//        candidatesViewModel.candidates.observe(viewLifecycleOwner, Observer { candidates ->
//            candidatesAdapter.submitList(candidates)
//        })

        // Показываем или скрываем элементы в зависимости от текущего фрагмента
        (activity as MainActivity).setUIVisibility(showHeader = true, showBottomNav = true)
    }

}