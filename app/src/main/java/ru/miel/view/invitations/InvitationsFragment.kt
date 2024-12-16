package ru.miel.view.invitations

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.miel.R
import ru.miel.databinding.FragmentInvitationsBinding
import ru.miel.domain.models.InvitationsCandidates

class InvitationsFragment : Fragment() {

    private var _binding: FragmentInvitationsBinding? = null
    private val binding get() = _binding!!

    private lateinit var invitationsAdapter: InvitationsAdapter

    private val invitations = listOf(
        InvitationsCandidates(img = R.drawable.img_avatar_candidates, name = "Романова Мария Ивановна" , employment = "Трудоустроен(а)", options = R.color.turquoise, inf = R.drawable.ic_more_inf ),
        InvitationsCandidates(img = R.drawable.img_avatar_candidates, name = "Романова Мария Ивановна" , employment = "Приглашен(а)", options = R.color.orange, inf = R.drawable.ic_more_inf ),
        InvitationsCandidates(img = R.drawable.img_avatar_candidates, name = "Романова Мария Ивановна" , employment = "Отказ", options = R.color.bordo, inf = R.drawable.ic_more_inf ),
    )

    companion object {
        fun newInstance() = InvitationsFragment()
    }

    private val viewModel: InvitationsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInvitationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        invitationsAdapter = InvitationsAdapter(invitations)
        binding.rcInvitationsCandidates.adapter = invitationsAdapter
    }
}