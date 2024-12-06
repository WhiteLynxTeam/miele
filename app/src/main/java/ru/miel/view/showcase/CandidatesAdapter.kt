package ru.miel.view.showcase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.miel.R
import ru.miel.databinding.ItemCandidatesBinding
import ru.miel.domain.models.Candidates

class CandidatesAdapter(
    private val onIconClick:(pos:Int) -> Unit,
    private val onButtonClick:(pos:Int) -> Unit
) : RecyclerView.Adapter<CandidatesAdapter.CandidatesViewHolder>() {

    private val candidatesList: MutableList<Candidates> = mutableListOf()

    // Обновление списка кандидатов
    fun submitList(newCandidates: List<Candidates>) {
        candidatesList.clear()
        candidatesList.addAll(newCandidates)
        notifyDataSetChanged()
    }

    inner class CandidatesViewHolder(private val binding: ItemCandidatesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(candidates: Candidates, position: Int) {
            binding.ivAvatar.setImageResource(candidates.img)
            binding.tvName.text = candidates.name
            binding.tvYear.text = candidates.year
            binding.tvCity.text = candidates.city
            binding.tvRealtor.text = candidates.realtor
            binding.tvJuridicalCourse.text = candidates.juridicalCourse
            binding.tvMortgage.text = candidates.mortgage
            binding.tvTaxation.text = candidates.taxation
            binding.tvObjects.text = candidates.objects
            binding.tvClients.text = candidates.clients

            binding.ivFavorites.setImageResource(if (candidates.isFavorite) R.drawable.ic_favorites_candidates_selected else R.drawable.ic_favorites)
            binding.btnInvite.text = if (candidates.isInvite) "Приглашен" else "Пригласить"
            binding.btnInvite.setBackgroundResource(if (candidates.isInvite) R.color.lime else R.color.magenta)

            binding.ivFavorites.setOnClickListener {
               onIconClick(position)
            }
            binding.btnInvite.setOnClickListener {
                onButtonClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidatesViewHolder {
        val binding = ItemCandidatesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CandidatesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CandidatesViewHolder, position: Int) {
        holder.bind(candidatesList[position], position)
    }

    override fun getItemCount() = candidatesList.size

//    interface OnIconClickListener {
//        fun onIconClick(position: Int)
//        fun onButtonClick(position: Int)
//    }
}