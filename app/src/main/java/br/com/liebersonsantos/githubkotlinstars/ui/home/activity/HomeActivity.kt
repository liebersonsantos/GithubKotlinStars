package br.com.liebersonsantos.githubkotlinstars.ui.home.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.liebersonsantos.githubkotlinstars.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}



