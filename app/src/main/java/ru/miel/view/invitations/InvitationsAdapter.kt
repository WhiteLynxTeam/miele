package ru.miel.view.invitations

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.miel.R
import ru.miel.databinding.ItemInvitationsBinding
import ru.miel.domain.models.InvitationsCandidatesFromApi

class InvitationsAdapter(
    private val onClick: (id: Int) -> Unit

) :
    RecyclerView.Adapter<InvitationsAdapter.InvitationsViewHolder>() {

    private var candidatesList: MutableList<InvitationsCandidatesFromApi> = mutableListOf()

   inner class InvitationsViewHolder(private val binding: ItemInvitationsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(candidates: InvitationsCandidatesFromApi) {
//            Glide.with(binding.root)
//                .load(candidates.photo)
//                .error(R.drawable.img_avatar)
//                .centerCrop()
//                .into(binding.ivAvatar)
            binding.tvName.text = "${candidates.id}. ${candidates.surname} ${candidates.name} ${candidates.patronymic}"
//            binding.tvEmploymentOptions.text = if (candidates.isInvited) "Приглашен" else "Не приглашен"
            binding.tvEmploymentOptions.text = candidates.status?.text() ?: "#error"
            binding.ibMoreInf.setImageResource(R.drawable.ic_more_inf)
            binding.employmentOptions.setBackgroundResource(candidates.status?.color() ?: R.color.white)

            binding.ibMoreInf.setOnClickListener {
               println("Работает = ${candidates.surname} ${candidates.name} ${candidates.patronymic} ${candidates.city} ${candidates.age} ${candidates.status} ${candidates.updatedAt}")
                onClick(candidates.candidateId)
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