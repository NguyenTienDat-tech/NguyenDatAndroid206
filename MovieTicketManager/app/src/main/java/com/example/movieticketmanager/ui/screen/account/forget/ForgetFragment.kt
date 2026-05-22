package com.example.movieticketmanager.ui.screen.account.forget

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.movieticketmanager.R
import com.example.movieticketmanager.data.local.database.AppDatabase
import com.example.movieticketmanager.data.repository.UserRepository
import com.example.movieticketmanager.databinding.FragmentForgetBinding
import kotlinx.coroutines.launch

class ForgetFragment : Fragment() {
    private lateinit var binding: FragmentForgetBinding
    private lateinit var repository: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forget, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForgetBinding.bind(view)

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

            if (email.isEmpty() ) {
                Toast.makeText(requireContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@launch
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(requireContext(), "Nhập không đúng email. Nhập lại!", Toast.LENGTH_SHORT).show()
                return@launch
            }

            val success = repository.checkEmail(email)

            if (success) {
                findNavController().navigate(
                    R.id.resetPasswordFragment,
                    Bundle().apply {
                        putString("email", email)
                    })
                return@launch
            }
            else {
                Toast.makeText(requireContext(), "Email không tồn tại!", Toast.LENGTH_SHORT).show()
                return@launch
            }

        }
    }

    private fun setOnclick() {
        binding.verify.setOnClickListener {
            ngoaiLe()
        }
    }


}