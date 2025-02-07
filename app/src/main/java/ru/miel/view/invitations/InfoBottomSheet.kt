package ru.miel.view.invitations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ru.miel.databinding.BottomSheetLayoutBinding
import ru.miel.domain.models.InvitationsCandidatesFromApi

@Suppress("UNREACHABLE_CODE")
class InfoBottomSheet : DialogFragment() {

    private val id: Int? by lazy { arguments?.getInt("id") }

    private lateinit var invitations: InvitationsCandidatesFromApi

    private lateinit var binding: BottomSheetLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetLayoutBinding.inflate(layoutInflater)
        return binding.root

        setCandidatesDetails()
    }

    fun setCandidatesDetails() {
        val fullName = invitations.surname + invitations.name + invitations.patronymic
        invitations = arguments?.get("invitations") as InvitationsCandidatesFromApi
        binding.tvName.text = fullName
        binding.tvCity.text = invitations.city
        binding.tvYear.inputType = invitations.age
        binding.tvStatus.text = invitations.status?.text() ?: "#error"
        binding.tvUpdated.text = invitations.updatedAt
    }

}