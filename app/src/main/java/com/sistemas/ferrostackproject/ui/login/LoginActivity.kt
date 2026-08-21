package com.sistemas.ferrostackproject.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sistemas.ferrostackproject.databinding.ActivityLoginBinding
import com.sistemas.ferrostackproject.ui.dashboard.DashboardActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarEventos()
    }

    private fun configurarEventos() {

        binding.btnLogin.setOnClickListener {

            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)

        }
    }
}