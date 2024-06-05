package es.ajrpachon.telefonicaproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.R
import dagger.hilt.android.AndroidEntryPoint
import es.ajrpachon.TelefonicaProject.databinding.FragmentNavHostActivityBinding

@AndroidEntryPoint
class NavHostActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentNavHostActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentNavHostActivityBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment_container
        ) as NavHostFragment
        navController = navHostFragment.navController
    }
}