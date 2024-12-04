package ru.miel.view.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.miel.databinding.ItemChatsBinding
import ru.miel.domain.models.Chats

class ChatsAdapter(private val chatList: List<Chats>) : RecyclerView.Adapter<ChatsAdapter.ChatsViewHolder>() {

    inner class ChatsViewHolder(private val binding: ItemChatsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chats: Chats) {
            binding.avatar.setImageResource(chats.img)
            binding.name.text = chats.name
            binding.message.text = chats.text
            binding.time.text = chats.time

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        val binding = ItemChatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        holder.bind(chatList[position])
    }

    override fun getItemCount() = chatList.size
}