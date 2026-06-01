package com.example.openningscreen.ui.screen.login

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
import com.example.openningscreen.data.sharedPreference.PrefsManager
import com.example.openningscreen.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            UserRepository(
                Room.databaseBuilder(
                    requireContext(),
                    AppDatabase::class.java,
                    "app_db"
                ).build().userDao()
            ),
            PrefsManager(requireContext())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

//        val prefs = PrefsManager(requireContext())
//        if (prefs.getLogin()) {
//            findNavController().navigate(R.id.layout1)
//            return
//        }

        setOnClick()
        stateData()
        eventData()
    }

    private fun setOnClick() {
        binding.eye.setOnClickListener {
            viewModel.changePassword()
        }

        binding.forgotPassword.setOnClickListener {
            viewModel.forgotCLick()
        }

        binding.signUp.setOnClickListener {
            viewModel.registerClick()
        }

        binding.inputEmail.addTextChangedListener { //addTextChangedListener: check khi nào người dùng nhập
            viewModel.onEmailChange(it.toString()) //it: giá trị hiện tại
        }

        binding.inputPassword.addTextChangedListener {
            viewModel.onPasswordChange(it.toString())
        }

        binding.login.setOnClickListener {
            viewModel.onLoginClick()
        }
    }

    private fun stateData() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { isVisible ->
                    //ChangePassword
                    if (isVisible.isPasswordVisible) {
                        binding.inputPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        binding.eye.setImageResource(R.drawable.eyeopen)
                    }
                    else {
                        binding.inputPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                        binding.eye.setImageResource(R.drawable.eyeclose)
                    }

                    binding.inputPassword.setSelection(binding.inputPassword.length())


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
        lifecycleScope.launch {
            viewModel.event.collect { event ->
                when (event) {
                    //navigationRegister
                    is LoginEvent.NavigationRegister -> {
                        findNavController().navigate(R.id.layout3)
                    }

                    //navigationForGot
                    is LoginEvent.NavigationForgot -> {
                        findNavController().navigate(R.id.layout4)
                    }

                    //navigationHome
                    is LoginEvent.NavigationHome -> {
                        findNavController().navigate(R.id.layout1)
                    }



                    //check null
                    is LoginEvent.Null -> {
                        Toast.makeText(requireContext(), event.text, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}