package ru.miel.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.miel.domain.models.enummodel.InvitationStatus

@Parcelize
data class InvitationsCandidatesFromApi(
    val id: Int,
    val name: String,
    val surname: String,
    val patronymic: String,
    val city: String,
    val age: Int,
    val status: InvitationStatus?,
    val updatedAt: String,
) : Parcelable
