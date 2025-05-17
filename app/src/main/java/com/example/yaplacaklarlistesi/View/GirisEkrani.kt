package com.example.yaplacaklarlistesi.View

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.yaplacaklarlistesi.databinding.ActivityGirisEkraniBinding

class GirisEkrani : AppCompatActivity() {
    private lateinit var binding: ActivityGirisEkraniBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGirisEkraniBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonGiris.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}