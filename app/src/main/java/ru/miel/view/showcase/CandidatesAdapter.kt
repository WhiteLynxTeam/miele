package ru.miel.view.showcase

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.miel.R
import ru.miel.databinding.ItemCandidatesBinding
import ru.miel.domain.models.Candidates
import ru.miel.domain.models.CandidatesFromApi

class CandidatesAdapter(
    private val onIconClick:(pos:Int) -> Unit,
    private val onButtonClick:(pos:Int) -> Unit
) : RecyclerView.Adapter<CandidatesAdapter.CandidatesViewHolder>() {

    private var candidatesList: MutableList<CandidatesFromApi> = mutableListOf()

    // Обновление списка кандидатов
//    fun submitList(newCandidates: List<Candidates>) {
//        candidatesList.clear()
//        candidatesList.addAll(newCandidates)
//        notifyDataSetChanged()
//    }

    inner class CandidatesViewHolder(private val binding: ItemCandidatesBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(candidates: CandidatesFromApi, position: Int) {

            Glide.with(binding.root)
                .load(candidates.photo)
                .error(R.drawable.img_avatar)
                .centerCrop()
                .into(binding.ivAvatar)
            binding.tvName.text = "${candidates.surname} ${candidates.name} ${candidates.patronymic}"
            binding.tvYear.text = candidates.birth
            binding.tvCity.text = candidates.city
            binding.tvRealtor.text = "Введение в профессию риелтор (${candidates.course_rieltor_join})"
            binding.tvJuridicalCourse.text = "Базовый юридический курс (${candidates.basic_legal_course})"
            binding.tvMortgage.text = "Курс “Ипотека” (${candidates.course_mortgage})"
            binding.tvTaxation.text = "Курс “Налогообложение” (${candidates.course_taxation})"
            binding.tvObjects.text = candidates.completed_objects.toString()
            binding.tvClients.text = candidates.clients.toString()
            binding.ivFavorites.setImageResource(if (candidates.isFavorite) R.drawable.ic_favorites_candidates_selected else R.drawable.ic_favorites)
            binding.btnInvite.text = if (candidates.isInvite) "Приглашен" else "Пригласить"
            binding.btnInvite.setBackgroundResource(if (candidates.isInvite) R.color.lime else R.color.bordo)

//            binding.ivAvatar.setImageResource(candidates.img)
//            binding.tvName.text = candidates.name
//            binding.tvYear.text = candidates.year
//            binding.tvCity.text = candidates.city
//            binding.tvRealtor.text = candidates.realtor
//            binding.tvJuridicalCourse.text = candidates.juridicalCourse
//            binding.tvMortgage.text = candidates.mortgage
//            binding.tvTaxation.text = candidates.taxation
//            binding.tvObjects.text = candidates.objects
//            binding.tvClients.text = candidates.clients
//
//            binding.ivFavorites.setImageResource(if (candidates.isFavorite) R.drawable.ic_favorites_candidates_selected else R.drawable.ic_favorites)
//            binding.btnInvite.text = if (candidates.isInvite) "Приглашен" else "Пригласить"
//            binding.btnInvite.setBackgroundResource(if (candidates.isInvite) R.color.lime else R.color.bordo)

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

    @SuppressLint("NotifyDataSetChanged")
    fun setData(candidates: List<CandidatesFromApi>) {
        this.candidatesList = candidates.toMutableList()
        notifyDataSetChanged()
    }
}