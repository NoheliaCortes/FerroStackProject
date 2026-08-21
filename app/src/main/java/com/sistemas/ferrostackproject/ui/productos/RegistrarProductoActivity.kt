package com.sistemas.ferrostackproject.ui.productos

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.sistemas.ferrostackproject.R
import com.sistemas.ferrostackproject.data.database.InventoryDatabase
import com.sistemas.ferrostackproject.data.entity.Producto
import com.sistemas.ferrostackproject.data.repository.ProductoRepository
import com.sistemas.ferrostackproject.viewmodel.ProductoViewModel
import com.sistemas.ferrostackproject.viewmodel.ProductoViewModelFactory

class RegistrarProductoActivity : AppCompatActivity() {

    // Inicialización del ViewModel mediante su Factory y Room
    private val viewModel: ProductoViewModel by viewModels {
        val database = InventoryDatabase.getDatabase(this)
        val repository = ProductoRepository(database.productoDao())
        ProductoViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_producto)

        // Referencias exactas a las vistas de tu XML
        val edtNombre = findViewById<TextInputEditText>(R.id.edtNombre)
        val edtPrecio = findViewById<TextInputEditText>(R.id.edtPrecio)
        val edtCantidad = findViewById<TextInputEditText>(R.id.edtCantidad)
        val actCategoria = findViewById<AutoCompleteTextView>(R.id.actCategoria)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)




        val categorias = arrayOf(
            "Herramientas Manuales",
            "Herramientas Eléctricas",
            "Construcción",
            "Fontanería",
            "Pinturas",
            "Eléctricos",
            "Cerrajería"
        )

// Crear el adaptador usando el layout por defecto de Android
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categorias)

// Asignar el adaptador al AutoCompleteTextView
        actCategoria.setAdapter(adapter)

        btnGuardar.setOnClickListener {
            val nombre = edtNombre.text.toString().trim()
            val precioStr = edtPrecio.text.toString().trim()
            val cantidadStr = edtCantidad.text.toString().trim()
            val categoria = actCategoria.text.toString().trim()

            // Validación de campos vacíos
            if (nombre.isEmpty() || precioStr.isEmpty() || cantidadStr.isEmpty() || categoria.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Conversión de datos
            val precio = precioStr.toDoubleOrNull() ?: 0.0
            val cantidad = cantidadStr.toIntOrNull() ?: 0

            // Creación del objeto Entidad
            val producto = Producto(
                nombre = nombre,
                precio = precio,
                cantidad = cantidad,
                categoria = categoria
            )

            // Guardar usando Room + ViewModel
            viewModel.guardarProducto(producto)

            Toast.makeText(this, "Producto guardado con éxito", Toast.LENGTH_LONG).show()

            // Limpiar formulario
            edtNombre.text?.clear()
            edtPrecio.text?.clear()
            edtCantidad.text?.clear()
            actCategoria.text?.clear()
        }
    }
}