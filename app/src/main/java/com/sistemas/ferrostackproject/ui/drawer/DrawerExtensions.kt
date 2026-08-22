package com.sistemas.ferrostackproject.ui.drawer

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageButton
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.sistemas.ferrostackproject.R
import com.sistemas.ferrostackproject.ui.configuration.ConfigurationActivity
import com.sistemas.ferrostackproject.ui.dashboard.DashboardActivity
import com.sistemas.ferrostackproject.ui.inventory.InventoryActivity
import com.sistemas.ferrostackproject.ui.login.LoginActivity
import com.sistemas.ferrostackproject.ui.productos.ProductActivity
import com.sistemas.ferrostackproject.ui.reports.ReportsActivity

fun Activity.setupDrawerNavigation(drawerLayout: DrawerLayout) {
    // 1. Abrir menú al presionar el botón de la barra superior
    val btnMenu = findViewById<ImageButton>(R.id.btnMenu)
    btnMenu?.setOnClickListener {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    // 2. Referencias a las opciones del menú lateral (drawerMenu)
    val itemDashboard = findViewById<View>(R.id.itemDashboard)
    val itemProductos = findViewById<View>(R.id.itemProductos)
    val itemInventario = findViewById<View>(R.id.itemInventario)
    val itemReportes = findViewById<View>(R.id.itemReportes)
    val itemConfiguracion = findViewById<View>(R.id.itemConfiguracion)
    val itemCerrarSesion = findViewById<View>(R.id.itemCerrarSesion)

    // Función auxiliar para realizar la transición entre pantallas de forma limpia
    fun navigateTo(targetActivity: Class<*>) {
        if (this::class.java != targetActivity) {
            startActivity(Intent(this, targetActivity))
            finish()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    // 3. Listeners de eventos de navegación
    itemDashboard?.setOnClickListener {
        navigateTo(DashboardActivity::class.java)
    }

    itemProductos?.setOnClickListener {
        navigateTo(ProductActivity::class.java)
    }

    itemInventario?.setOnClickListener {
        navigateTo(InventoryActivity::class.java)
    }

    itemReportes?.setOnClickListener {
        navigateTo(ReportsActivity::class.java)
    }

    itemConfiguracion?.setOnClickListener {
        navigateTo(ConfigurationActivity::class.java)
    }

    itemCerrarSesion?.setOnClickListener {
        drawerLayout.closeDrawer(GravityCompat.START)
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}