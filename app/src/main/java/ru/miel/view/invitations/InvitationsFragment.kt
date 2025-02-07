package ru.miel.view.invitations

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import ru.miel.R
import ru.miel.databinding.FragmentInvitationsBinding
import ru.miel.domain.models.InvitationsCandidatesFromApi
import ru.miel.view.activity.MainActivity
import javax.inject.Inject

class InvitationsFragment : Fragment() {

    private var _binding: FragmentInvitationsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: InvitationsViewModel

    @Inject
    lateinit var vmFactory: InvitationsViewModel.Factory

    private val invitationsAdapter by lazy {
       InvitationsAdapter { candidate ->
           findNavController().navigate(R.id.action_invitationsFragment_to_infoBottomSheet,
               Bundle().apply {
                   putParcelable("candidate", candidate)
               }
            )
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

//    private val invitations = listOf(
//        InvitationsCandidates(img = R.drawable.img_avatar_candidates, name = "Романова Мария Ивановна" , employment = "Трудоустроен(а)", options = R.color.turquoise, inf = R.drawable.ic_more_inf ),
//        InvitationsCandidates(img = R.drawable.img_avatar_candidates, name = "Романова Мария Ивановна" , employment = "Приглашен(а)", options = R.color.orange, inf = R.drawable.ic_more_inf ),
//        InvitationsCandidates(img = R.drawable.img_avatar_candidates, name = "Романова Мария Ивановна" , employment = "Отказ", options = R.color.bordo, inf = R.drawable.ic_more_inf ),
//    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInvitationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[InvitationsViewModel::class.java]

        binding.rcInvitationsCandidates.adapter = invitationsAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.candidates.collect {
                invitationsAdapter.setData(it)
            }
        }

        viewModel.getInvitations()
    }
}

//Как с помошью сеарилизацию прередать класс через бандл из фрагмента во фрагменит