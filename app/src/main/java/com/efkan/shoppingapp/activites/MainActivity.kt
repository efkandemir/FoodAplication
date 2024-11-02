package com.efkan.shoppingapp.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.efkan.shoppingapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    //private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        //val view=binding.root
        setContentView(R.layout.activity_main)

    }
    override fun onStart() {
        super.onStart()
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.btn_nav)
        val navController = findNavController(R.id.hostFragment)
        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }
}