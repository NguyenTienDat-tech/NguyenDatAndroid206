package com.example.openningscreen.ui.screen.register

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
import com.example.openningscreen.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels() {
        RegistoryViewModelFactory(
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
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterBinding.bind(view)

        setOnCLick()
        stateData()
        eventData()
    }

    private fun setOnCLick() {
        binding.eye.setOnClickListener {
            viewModel.changePassword()
        }

        binding.login1.setOnClickListener {
            viewModel.loginClick()
        }

        binding.inputName.addTextChangedListener {
            viewModel.onNameChange(it.toString())
        }

        binding.inputEmail.addTextChangedListener {
            viewModel.onEmailChange(it.toString())
        }

        binding.inputPassword.addTextChangedListener {
            viewModel.onPasswordChange(it.toString())
        }

        binding.login.setOnClickListener {
            viewModel.onRegisterClick()
        }
    }

    private fun stateData() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
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

                    //check Name
                    if (binding.inputName.text.toString() != isVisible.name) {
                        binding.inputName.setText(isVisible.name)
                    }

                    //check email
                    if (binding.inputEmail.text.toString() != isVisible.email) {
                        binding.inputEmail.setText(isVisible.email)
                    }

                    //check password
                    if (binding.inputPassword.text.toString() != isVisible.password) {
                        binding.inputPassword.setText(isVisible.password)
                    }
                }
            }
        }
    }

    private fun eventData() {
        //navigationLogin
        lifecycleScope.launch {
            viewModel.event.collect { event ->
                when (event) {
                    is RegisterEvent.NavigationLogin -> {
                        findNavController().navigate(R.id.layout2)
                    }

                    is RegisterEvent.Null -> {
                        Toast.makeText(requireContext(), event.text, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


}