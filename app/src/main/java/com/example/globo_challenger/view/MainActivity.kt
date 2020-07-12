package com.example.globo_challenger.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.globo_challenger.R
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.get
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.globo_challenger.model.Content
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), MainView {

    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController
        navController?.let {
            val appBarConfiguration = AppBarConfiguration(it.graph)
            toolbar?.setupWithNavController(it, appBarConfiguration)
        }
    }

    override fun goDetail(content: Content) {
        navController?.graph?.get(R.id.detail_fragment)?.label = content.secao?.nome?.toUpperCase(Locale.getDefault())
        navController?.navigate(R.id.detail_fragment, DetailFragmentArgs(content).toBundle())
    }

    override fun onBackPressed() {
        val backPressWasConsumed = navController?.popBackStack() == true
        if (!backPressWasConsumed) {
            super.onBackPressed()
        }
    }
}