package ru.miel.view.greetings

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.miel.R
import ru.miel.databinding.FragmentGreetingBinding
import ru.miel.view.showcase.ShowcaseFragment

class GreetingFragment : Fragment() {

    private var _binding: FragmentGreetingBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(): GreetingFragment {
            return GreetingFragment()
        }
    }

    private val viewModel: GreetingViewModel by viewModels()

    //При переходе на временный фрагмент запускает его на 1.5 секунд
    override fun onStart() {
        super.onStart()
        Handler(Looper.getMainLooper()).postDelayed({
            openShowcassFragment()
        }, 1000L)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGreetingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //пока для проверки, потом подтянем с БД
        val userName = "Мария"
        binding.tvUsersGreeting.text = "Добро пожаловать, $userName!"
    }


    private fun openShowcassFragment() {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_placeholder, ShowcaseFragment())
        transaction.commit()
    }
}