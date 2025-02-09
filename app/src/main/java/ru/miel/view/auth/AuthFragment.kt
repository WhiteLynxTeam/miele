package ru.miel.view.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import ru.miel.R
import ru.miel.databinding.FragmentAuthBinding
import ru.miel.domain.models.User
import ru.miel.view.activity.MainActivity
import ru.miel.utils.uiextensions.showSnackbarLong
import javax.inject.Inject

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var vmFactory: AuthViewModel.Factory

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

        viewModel =
            ViewModelProvider(this, vmFactory)[AuthViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isEntry.collect {
//              if (it) findNavController().navigate(R.id.action_authFragment_to_showcaseFragment)
                if (it) findNavController().navigate(R.id.action_authFragment_to_greetingFragment)
                else {
                    showSnackbarLong("Ошибка авторизации.")
                }
            }
        }

        // Показываем или скрываем элементы в зависимости от текущего фрагмента
        (activity as MainActivity).setUIVisibility(
            showHeader = false,
            showBottomNav = false,
        )

        binding.btnSignIn.setOnClickListener {
//            findNavController().navigate(R.id.action_authFragment_to_homeFragment)
            if (binding.etLogin.text.toString().isEmpty() || binding.etPassword.text.toString()
                    .isEmpty()
            ) {
                showSnackbarLong("Заполните поля.")
                return@setOnClickListener
            }

            viewModel.auth(
                User(
                    username = binding.etLogin.text.toString(),
                    password = binding.etPassword.text.toString(),
                )
            )
        }
    }
}