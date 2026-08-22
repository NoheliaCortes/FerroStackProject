package com.sistemas.ferrostackproject.ui.inventory

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.sistemas.ferrostackproject.R
import com.sistemas.ferrostackproject.ui.drawer.setupDrawerNavigation

class InventoryActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inventory)

        // Referencia al DrawerLayout del XML de Configuración
        drawerLayout = findViewById(R.id.drawerLayout)

        // Configurar la barra superior y la navegación del menú lateral
        setupDrawerNavigation(drawerLayout)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}