package com.sistemas.ferrostackproject.ui.productos

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sistemas.ferrostackproject.R
import com.sistemas.ferrostackproject.data.database.InventoryDatabase
import com.sistemas.ferrostackproject.data.repository.ProductoRepository
import com.sistemas.ferrostackproject.viewmodel.ProductoViewModel
import com.sistemas.ferrostackproject.viewmodel.ProductoViewModelFactory

class ProductActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var txtCantidadProductos: TextView
    private lateinit var recyclerProductos: RecyclerView
    private lateinit var adapter: ProductoAdapter

    // Inicialización del ViewModel mediante su Factory y la base de datos Room
    private val viewModel: ProductoViewModel by viewModels {
        val database = InventoryDatabase.getDatabase(this)
        val repository = ProductoRepository(database.productoDao())
        ProductoViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        // Referencias a los elementos de la interfaz
        drawerLayout = findViewById(R.id.drawerLayout)
        val btnMenu = findViewById<ImageButton>(R.id.btnMenu)
        val btnAgregarProducto = findViewById<FloatingActionButton>(R.id.btnAgregarProducto)
        txtCantidadProductos = findViewById(R.id.txtCantidadProductos)
        recyclerProductos = findViewById(R.id.recyclerProductos)

        // Configuración del RecyclerView y Adaptador
        adapter = ProductoAdapter()
        recyclerProductos.layoutManager = LinearLayoutManager(this)
        recyclerProductos.adapter = adapter

        // Observar la lista de productos enviada por el ViewModel
        viewModel.listaProductos.observe(this) { lista ->
            adapter.actualizarLista(lista)
            txtCantidadProductos.text = "${lista.size} productos"
        }

        // Abrir Menú Lateral al presionar el botón de la barra superior
        btnMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Navegar a la pantalla de Registro al presionar el botón flotante (+)
        btnAgregarProducto.setOnClickListener {
            val intent = Intent(this, RegistrarProductoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Carga o refresca la lista cada vez que regresas a esta pantalla tras registrar un producto
        viewModel.obtenerProductos()
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