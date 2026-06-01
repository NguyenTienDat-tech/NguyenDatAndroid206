package com.example.openningscreen.ui.screen.forgotpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.openningscreen.R
import com.example.openningscreen.data.local.database.AppDatabase
import com.example.openningscreen.data.repository.UserRepository
import com.example.openningscreen.databinding.FragmentForgotpasswordBinding
import kotlinx.coroutines.launch

class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotpasswordBinding
    private val viewModel : ForgotViewModel by viewModels() {
        ForgotViewModelFactory(
            UserRepository(
                Room.databaseBuilder(
                    requireContext(),
                    AppDatabase::class.java,
                    "app_db"
                ).build().userDao()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgotpassword, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentForgotpasswordBinding.bind(view)

        setOnClick()
        stateData()
        eventData()
    }

    private fun setOnClick() {
        binding.vector.setOnClickListener {
            viewModel.loginClick()
        }

        binding.inputEmail.addTextChangedListener {
            viewModel.onEmailChange(it.toString())
        }

        binding.sendCode.setOnClickListener {
            viewModel.onSendClick()
        }
    }

    private fun stateData() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    //checkEmail
                    if (binding.inputEmail.text.toString() != state.email) {
                        binding.inputEmail.setText(state.email)
                    }
                }
            }
        }
    }

    private fun eventData() {
        lifecycleScope.launch {
            viewModel.event.collect { event ->
                when (event) {
                    is ForgotEvent.NavigationOTPSendEmail -> {
                        findNavController().navigate(
                            R.id.layout5,
                            Bundle().apply {
                                putString("email", event.email)
                            }
                        )
                    }

                    is ForgotEvent.NavigationOTP -> {
                        findNavController().navigate(R.id.layout5)
                    }

                    is ForgotEvent.NavigationLogin -> {
                        findNavController().navigate(R.id.layout2)
                    }

                    is ForgotEvent.Null -> {
                        Toast.makeText(requireContext(), event.text, Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }

}