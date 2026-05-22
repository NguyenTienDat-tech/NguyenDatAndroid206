package com.example.movieticketmanager.ui.screen.account.login

import android.content.Intent
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
import androidx.room.Room
import com.example.movieticketmanager.R
import com.example.movieticketmanager.data.local.database.AppDatabase
import com.example.movieticketmanager.data.repository.UserRepository
import com.example.movieticketmanager.databinding.FragmentLoginBinding
import com.example.movieticketmanager.ui.screen.admin.mainActivitiAdmin.MainActivityAdmin
import com.example.movieticketmanager.ui.screen.user.mainActivityUser.MainActivityUser
import kotlinx.coroutines.launch


class LoginFragment: Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private var isPassword = false
    private var isBox1 = false
    private var isBox2 = false
    private lateinit var repository: UserRepository



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

            if (email.isEmpty() && password.isEmpty()) {
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

            val success = repository.login(email, password)

            if (success && isBox1) {
                val intent = Intent(requireContext(), MainActivityAdmin::class.java)
                startActivity(intent)
                return@launch
            }
            else if (success && isBox2) {
                val intent = Intent(requireContext(), MainActivityUser::class.java)
                startActivity(intent)
                return@launch
            }
            else {
                Toast.makeText(requireContext(), "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show()
                return@launch
            }
        }
    }

    private fun setOnclick() {
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

        binding.login.setOnClickListener {
            ngoaiLe()
        }

        binding.forgetPassword.setOnClickListener {
            findNavController().navigate(R.id.forgetFragment)
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

        binding.box1.setOnClickListener {
            isBox1 = !isBox1

            if (isBox1) {
                binding.box1.setImageResource(R.drawable.square_green)
            }
            else {
                binding.box1.setImageResource(R.drawable.square)
            }
        }

        binding.box2.setOnClickListener {
            isBox2 = !isBox2

            if (isBox2) {
                binding.box2.setImageResource(R.drawable.square_green)
            }
            else {
                binding.box2.setImageResource(R.drawable.square)
            }
        }

    }


}