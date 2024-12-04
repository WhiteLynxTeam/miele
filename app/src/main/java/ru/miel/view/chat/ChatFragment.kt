package ru.miel.view.chat

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import ru.miel.R
import ru.miel.databinding.FragmentChatBinding
import ru.miel.domain.models.Chats
import ru.miel.view.activity.MainActivity

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ChatViewModel

    private val chats = mutableListOf(
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
        Chats(R.drawable.img_avatar, "Романова Мария", "Lorem ipsum dolor sit amet consectetur.", "14:36"),
    )

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rcChats.adapter = ChatsAdapter(chats)

        // Показываем или скрываем элементы в зависимости от текущего фрагмента
        (activity as MainActivity).setUIVisibility(showHeader = true, showBottomNav = true)
    }
}