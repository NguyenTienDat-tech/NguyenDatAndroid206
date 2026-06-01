package com.example.openningscreen.ui.screen.otp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.openningscreen.R
import com.example.openningscreen.data.local.database.AppDatabase
import com.example.openningscreen.data.repository.UserRepository
import com.example.openningscreen.databinding.FragmentOtpverificationBinding
import kotlinx.coroutines.launch

class OtpVerificationFragment : Fragment() {
    private lateinit var binding: FragmentOtpverificationBinding
    private val viewModel: OtpViewModel by viewModels() {
        OTPViewModelFactory(
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
        return inflater.inflate(R.layout.fragment_otpverification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentOtpverificationBinding.bind(view)

        setOnCLick()
        eventData()
    }

    private fun setOnCLick() {
        binding.verify.setOnClickListener {
            val email = arguments?.getString("email") ?: ""
            viewModel.resetClick(email)
        }

        binding.vector.setOnClickListener {
            viewModel.forgotClick()
        }
    }

    private fun eventData() {
        lifecycleScope.launch {
            viewModel.event.collect { event ->
                when (event) {
                    is OtpEvent.NavigationForgot -> {
                        findNavController().navigate(R.id.layout4)
                    }

                    is OtpEvent.NavigationReset -> {
                        findNavController().navigate(R.id.layout6)
                    }

                    is OtpEvent.NavigationResetSendEmail -> {
                        findNavController().navigate(
                            R.id.layout6,
                            Bundle().apply {
                                putString("email", event.email)
                            }
                        )
                    }
                }
            }
        }
    }
}