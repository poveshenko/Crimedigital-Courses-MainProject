package com.example.matchresults


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.matchresults.databinding.ActivityMainMenuBinding
import com.example.matchresults.screens.ButtonDetailsFragment
import com.example.matchresults.screens.MatchListFragment


//Главный экран приложения, содержит нижнее меню для переключения между фрагментами.

class MainMenu : AppCompatActivity() {

    private val matchFragment = MatchListFragment()
    private val detailsFragment = ButtonDetailsFragment()
    lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Подключение нижнего меню
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.matches -> replaceFragment(matchFragment)
                R.id.details -> replaceFragment(detailsFragment)
            }
            true
        }

        replaceFragment(matchFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}
