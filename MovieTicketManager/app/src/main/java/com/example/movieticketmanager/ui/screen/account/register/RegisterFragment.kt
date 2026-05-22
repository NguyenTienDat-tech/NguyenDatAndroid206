package com.example.movieticketmanager.ui.screen.account.register

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Entity
import androidx.room.Room
import com.example.movieticketmanager.R
import com.example.movieticketmanager.data.local.database.AppDatabase
import com.example.movieticketmanager.data.local.entity.UserEntity
import com.example.movieticketmanager.data.repository.UserRepository
import com.example.movieticketmanager.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private var isPassword = false
    private var isPassword1 = false
    private lateinit var repository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        repository = UserRepository(
            Room.databaseBuilder(
                requireContext(),
                AppDatabase::class.java,
                "app_db"
            ).build().userDao()
        )

        setOnclick()
    }

    private fun ngoaiLe() {
        lifecycleScope.launch {
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()
            val password1 = binding.password1.text.toString().trim()

            if (email.isEmpty() && password.isEmpty() && password1.isEmpty()) {
                Toast.makeText(requireContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@launch
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(requireContext(), "Nhập không đúng email. Nhập lại!", Toast.LENGTH_SHORT).show()
                return@launch
            }
            else if (password.length < 6) {
                Toast.makeText(requireContext(), "Mật khẩu quá ngắn, vui lòng nhập nhiều hơn!", Toast.LENGTH_SHORT).show()
                return@launch
            }
            else if (password1.length < 6) {
                Toast.makeText(requireContext(), "Mật khẩu quá ngắn, vui lòng nhập nhiều hơn!", Toast.LENGTH_SHORT).show()
                return@launch
            }
            else if (password != password1) {
                Toast.makeText(requireContext(), "Mật khẩu không trùng nhau!", Toast.LENGTH_SHORT).show()
                return@launch
            }

            val entity = UserEntity(email=email, password=password)
            val success = repository.check(entity)

            if (success) {
                repository.insertUser(entity)
                findNavController().navigate(R.id.loginFragment)
                return@launch
            }
            else {
                Toast.makeText(requireContext(), "Email da ton tai!", Toast.LENGTH_SHORT).show()
                return@launch
            }
        }
    }

    private fun setOnclick() {
        binding.register.setOnClickListener {
            ngoaiLe()
        }
        binding.login.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        binding.eye.setOnClickListener {
            isPassword = !isPassword

            if (isPassword) {
                binding.password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.eye.setImageResource(R.drawable.eyeopen)
            }
            else {
                binding.password.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.eye.setImageResource(R.drawable.eyeclose)
            }

            binding.password.setSelection(binding.password.length())
        }

        binding.eye1.setOnClickListener {
            isPassword1 = !isPassword1

            if (isPassword1) {
                binding.password1.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.eye1.setImageResource(R.drawable.eyeopen)
            }
            else {
                binding.password1.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.eye1.setImageResource(R.drawable.eyeclose)
            }

            binding.password1.setSelection(binding.password1.length())
        }
    }


}