package ru.miel.view.invitations

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ru.miel.databinding.BottomSheetLayoutBinding
import ru.miel.domain.models.InvitationsCandidatesFromApi

@Suppress("UNREACHABLE_CODE")
class InfoBottomSheet : DialogFragment() {


    private val candidate: InvitationsCandidatesFromApi? by lazy {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) // API 33
            @Suppress("DEPRECATION")
            arguments?.getParcelable<InvitationsCandidatesFromApi>("candidate")
        else
            arguments?.getParcelable("candidate", InvitationsCandidatesFromApi::class.java)
    }

    private lateinit var invitations: InvitationsCandidatesFromApi

    private lateinit var binding: BottomSheetLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        candidate?.let { setCandidatesDetails(it) }
    }

    fun setCandidatesDetails(candidate: InvitationsCandidatesFromApi) {
        val fullName = "${candidate.surname} ${candidate.name} ${candidate.patronymic}"
        binding.tvName.text = fullName
        binding.tvCity.text = candidate.city
        binding.tvYear.inputType = candidate.age
        binding.tvStatus.text = candidate.status?.text() ?: "#error"
        binding.tvUpdated.text = candidate.updatedAt
    }

}