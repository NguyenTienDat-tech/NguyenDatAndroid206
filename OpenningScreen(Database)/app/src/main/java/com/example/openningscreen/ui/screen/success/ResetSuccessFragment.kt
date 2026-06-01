package com.example.openningscreen.ui.screen.success

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.openningscreen.R
import com.example.openningscreen.databinding.FragmentResetsuccessBinding
import kotlinx.coroutines.launch

class ResetSuccessFragment : Fragment() {
    private lateinit var binding: FragmentResetsuccessBinding
    private val viewModel: SuccessViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resetsuccess, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentResetsuccessBinding.bind(view)

        setOnCLick()
        eventData()
    }

    private fun setOnCLick() {
        binding.backToLogin.setOnClickListener {
            viewModel.loginClick()
        }
    }

    private fun eventData() {
        lifecycleScope.launch {
            viewModel.event.collect { event ->
                when (event) {
                    is SuccessEvent.NavigationLogin -> {
                        findNavController().navigate(R.id.layout7_layout2)
                    }

                }
            }
        }
    }


}