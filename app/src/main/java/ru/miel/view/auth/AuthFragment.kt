package ru.miel.view.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
import ru.miel.R
import ru.miel.databinding.FragmentAuthBinding
import ru.miel.view.activity.MainActivity

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AuthViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Показываем или скрываем элементы в зависимости от текущего фрагмента
        (activity as MainActivity).setUIVisibility(showHeader = false, showBottomNav = false)

        binding.btnSignIn.setOnClickListener{
            findNavController().navigate(R.id.action_authFragment_to_homeFragment)
        }
    }

}