package ru.miel.view.invitations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.miel.databinding.ItemInvitationsBinding
import ru.miel.domain.models.InvitationsCandidates

class InvitationsAdapter(private val invitationsList: List<InvitationsCandidates>) :
    RecyclerView.Adapter<InvitationsAdapter.InvitationsViewHolder>() {
    class InvitationsViewHolder(private val binding: ItemInvitationsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(invitationsCandidates: InvitationsCandidates) {
            binding.ivAvatar.setImageResource(invitationsCandidates.img)
            binding.tvName.text = invitationsCandidates.name
            binding.tvEmploymentOptions.text = invitationsCandidates.employment
            binding.ibMoreInf.setImageResource(invitationsCandidates.inf)
            binding.employmentOptions.setBackgroundResource(invitationsCandidates.options)
            /*binding.employmentOptions.setBackgroundResource(if (invitationsCandidates.options) R.color.turquoise else R.color.orange)*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvitationsViewHolder {
        val binding =
            ItemInvitationsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InvitationsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InvitationsViewHolder, position: Int) {
        holder.bind(invitationsList[position])
    }

    override fun getItemCount() = invitationsList.size
}