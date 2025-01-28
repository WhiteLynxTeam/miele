package ru.miel.view.greetings

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import ru.miel.R
import ru.miel.databinding.FragmentAuthBinding
import ru.miel.databinding.FragmentGreetingBinding
import ru.miel.view.auth.AuthViewModel
import javax.inject.Inject

class GreetingFragment : Fragment() {

    private var _binding: FragmentGreetingBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GreetingViewModel

    @Inject
    lateinit var vmFactory: GreetingViewModel.Factory


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    //При переходе на временный фрагмент запускает его на 1.5 секунд
    override fun onStart() {
        super.onStart()
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_greetingFragment_to_showcaseFragment)
        }, 1500L)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGreetingBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[GreetingViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fullName.collect {
                binding.tvUsersGreeting.text = "Добро пожаловать, $it!"
            }
        }

        //пока для проверки, потом подтянем с БД
//        val userName = "Мария"
//        binding.tvUsersGreeting.text = "Добро пожаловать, $userName!"

        viewModel.getFullName()
    }
}