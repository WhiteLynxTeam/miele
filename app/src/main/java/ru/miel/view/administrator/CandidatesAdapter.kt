package ru.miel.view.administrator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.miel.databinding.ItemCandidatesBinding
import ru.miel.domain.models.Candidates

class CandidatesAdapter(private val candidatesList: List<Candidates>) : RecyclerView.Adapter<CandidatesAdapter.CandidatesViewHolder>() {

    class CandidatesViewHolder(private val binding: ItemCandidatesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(candidates: Candidates) {
            binding.ivAvatar.setImageResource(candidates.img)
            binding.tvName.text = candidates.name
            binding.tvYear.text = candidates.year
            binding.tvCity.text = candidates.city
            binding.ivFavorites.setImageResource(candidates.icon)
            binding.tvRealtor.text = candidates.realtor
            binding.tvJuridicalCourse.text = candidates.juridicalCourse
            binding.tvMortgage.text = candidates.mortgage
            binding.tvTaxation.text = candidates.taxation
            binding.tvObjects.text = candidates.objects
            binding.tvClients.text = candidates.clients
            binding.btnInvite.text = candidates.buttonText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidatesViewHolder {
        val binding = ItemCandidatesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CandidatesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CandidatesViewHolder, position: Int) {
        holder.bind(candidatesList[position])
    }

    override fun getItemCount() = candidatesList.size
}