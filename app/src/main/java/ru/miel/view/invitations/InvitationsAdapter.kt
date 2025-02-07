package ru.miel.view.invitations

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.miel.R
import ru.miel.databinding.ItemInvitationsBinding
import ru.miel.domain.models.InvitationsCandidatesFromApi

class InvitationsAdapter(
    private val onClick: (candidate: InvitationsCandidatesFromApi) -> Unit

) :
    RecyclerView.Adapter<InvitationsAdapter.InvitationsViewHolder>() {

    private var candidatesList: MutableList<InvitationsCandidatesFromApi> = mutableListOf()

   inner class InvitationsViewHolder(private val binding: ItemInvitationsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(candidate: InvitationsCandidatesFromApi) {
//            Glide.with(binding.root)
//                .load(candidates.photo)
//                .error(R.drawable.img_avatar)
//                .centerCrop()
//                .into(binding.ivAvatar)
            binding.tvName.text = "${candidate.id}. ${candidate.surname} ${candidate.name} ${candidate.patronymic}"
//            binding.tvEmploymentOptions.text = if (candidates.isInvited) "Приглашен" else "Не приглашен"
            binding.tvEmploymentOptions.text = candidate.status?.text() ?: "#error"
            binding.ibMoreInf.setImageResource(R.drawable.ic_more_inf)
            binding.employmentOptions.setBackgroundResource(candidate.status?.color() ?: R.color.white)

            binding.ibMoreInf.setOnClickListener {
               println("Работает = ${candidate.surname} ${candidate.name} ${candidate.patronymic} ${candidate.city} ${candidate.age} ${candidate.status} ${candidate.updatedAt}")
                onClick(candidate)
            }
/*            binding.ivAvatar.setImageResource(invitationsCandidates.img)
            binding.tvName.text = invitationsCandidates.name
            binding.tvEmploymentOptions.text = invitationsCandidates.employment
            binding.ibMoreInf.setImageResource(invitationsCandidates.inf)
            binding.employmentOptions.setBackgroundResource(invitationsCandidates.options)*/
            /*binding.employmentOptions.setBackgroundResource(if (invitationsCandidates.options) R.color.turquoise else R.color.orange)*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvitationsViewHolder {
        val binding =
            ItemInvitationsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InvitationsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InvitationsViewHolder, position: Int) {
        holder.bind(candidatesList[position])
    }

    override fun getItemCount() = candidatesList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(candidates: List<InvitationsCandidatesFromApi>) {
        this.candidatesList = candidates.toMutableList()
        notifyDataSetChanged()
    }
}