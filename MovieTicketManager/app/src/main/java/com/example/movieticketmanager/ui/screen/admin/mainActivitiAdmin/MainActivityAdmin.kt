package com.example.movieticketmanager.ui.screen.admin.mainActivitiAdmin

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.movieticketmanager.R
import com.example.movieticketmanager.databinding.ActivityMainAdminBinding

class MainActivityAdmin : AppCompatActivity() {
    private lateinit var binding: ActivityMainAdminBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNav()
        setupBottom()
    }

    private fun setupNav() {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host1) as NavHostFragment
        navController = navHost.navController
    }

    private fun setupBottom() {
        binding.bottomNav.setupWithNavController(navController)
    }
}