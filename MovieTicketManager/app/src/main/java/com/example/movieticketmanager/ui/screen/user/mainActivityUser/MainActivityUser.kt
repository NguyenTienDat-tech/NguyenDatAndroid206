package com.example.movieticketmanager.ui.screen.user.mainActivityUser

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.movieticketmanager.R
import com.example.movieticketmanager.databinding.ActivityMainUserBinding

class MainActivityUser : AppCompatActivity() {
    private lateinit var binding: ActivityMainUserBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNav()
        setupBottom()
    }

    private fun setupNav() {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host2) as NavHostFragment
        navController = navHost.navController
    }

    private fun setupBottom() {
        binding.bottomNav1.setupWithNavController(navController)
    }
}