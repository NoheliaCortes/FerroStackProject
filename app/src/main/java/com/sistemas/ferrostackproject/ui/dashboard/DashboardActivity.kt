package com.sistemas.ferrostackproject.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sistemas.ferrostackproject.databinding.ActivityDashboardBinding
import com.sistemas.ferrostackproject.ui.configuration.ConfigurationActivity
import com.sistemas.ferrostackproject.ui.drawer.setupDrawerNavigation
import com.sistemas.ferrostackproject.ui.inventory.InventoryActivity
import com.sistemas.ferrostackproject.ui.productos.ProductActivity
import com.sistemas.ferrostackproject.ui.reports.ReportsActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Conectar la navegación general del menú lateral
        setupDrawerNavigation(binding.drawerLayout)

        configurarNavegacionCards()
    }

    private fun configurarNavegacionCards() {
        // NAVEGACIÓN DESDE LAS TARJETAS DEL DASHBOARD
        binding.cardProductos.setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }

        binding.cardInventario?.setOnClickListener {
            startActivity(Intent(this, InventoryActivity::class.java))
        }

        binding.cardReportes?.setOnClickListener {
            startActivity(Intent(this, ReportsActivity::class.java))
        }

        binding.cardConfiguracion?.setOnClickListener {
            startActivity(Intent(this, ConfigurationActivity::class.java))
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}