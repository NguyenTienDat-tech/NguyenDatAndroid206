package com.example.openningscreen.ui.screen.resetpassword

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
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
import com.example.openningscreen.databinding.FragmentResetpasswordBinding
import com.example.openningscreen.ui.screen.register.RegistoryViewModelFactory
import kotlinx.coroutines.launch

class ResetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentResetpasswordBinding
    private val viewModel: ResetViewModel by viewModels() {
        ResetViewModelFactory(
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
        return inflater.inflate(R.layout.fragment_resetpassword, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentResetpasswordBinding.bind(view)

        setOnCLick()
        stateData()
        eventData()
    }

    private fun setOnCLick() {
        binding.resetPassword.setOnClickListener {
            viewModel.successClick()
        }

        binding.vector.setOnClickListener {
            viewModel.otpClick()
        }

        binding.eye.setOnClickListener {
            viewModel.changePassword()
        }

        binding.eye1.setOnClickListener {
            viewModel.changePassword1()
        }

        binding.inputPassword.addTextChangedListener {
            viewModel.onPasswordChange(it.toString())
        }

        binding.inputPassword1.addTextChangedListener {
            viewModel.onPasswordChange1(it.toString())
        }

        binding.resetPassword.setOnClickListener {
            val email = arguments?.getString("email") ?: ""
            viewModel.onResetClick(email)
        }
    }

    private fun stateData() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { isVisible ->
                    //isPassword
                    if (isVisible.isPasswordVisible) {
                        binding.inputPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        binding.eye.setImageResource(R.drawable.eyeopen)
                    }
                    else {
                        binding.inputPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                        binding.eye.setImageResource(R.drawable.eyeclose)
                    }

                    binding.inputPassword.setSelection(binding.inputPassword.length())


                    //isPassword1
                    if (isVisible.isPasswordVisible1) {
                        binding.inputPassword1.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        binding.eye1.setImageResource(R.drawable.eyeopen)
                    }
                    else {
                        binding.inputPassword1.transformationMethod = PasswordTransformationMethod.getInstance()
                        binding.eye1.setImageResource(R.drawable.eyeclose)
                    }

                    binding.inputPassword1.setSelection(binding.inputPassword1.length())


                    //check password
                    if (binding.inputPassword.text.toString() != isVisible.password) {
                        binding.inputPassword.setText(isVisible.password)
                    }

                    //check password1
                    if (binding.inputPassword1.text.toString() != isVisible.password1) {
                        binding.inputPassword1.setText(isVisible.password1)
                    }
                }
            }
        }
    }

    private fun eventData() {
        lifecycleScope.launch {
            viewModel.event.collect { event ->
                when (event) {
                    is ResetEvent.NavigationOTP -> {
                        findNavController().navigate(R.id.layout5)
                    }

                    is ResetEvent.NavigationSuccess -> {
                        findNavController().navigate(R.id.layout7)
                    }

                    is ResetEvent.Null -> {
                        Toast.makeText(requireContext(), event.text, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


}