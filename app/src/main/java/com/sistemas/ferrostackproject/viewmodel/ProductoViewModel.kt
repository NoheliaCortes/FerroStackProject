package com.sistemas.ferrostackproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sistemas.ferrostackproject.data.entity.Producto
import com.sistemas.ferrostackproject.data.repository.ProductoRepository
import kotlinx.coroutines.launch

class ProductoViewModel(
    private val repository: ProductoRepository
) : ViewModel() {

    // LiveData que observará la Activity para pintar la lista en el RecyclerView
    val listaProductos = MutableLiveData<List<Producto>>()

    // Función para consultar todos los productos desde la base de datos
    fun obtenerProductos() {
        viewModelScope.launch {
            val productos = repository.obtenerTodos()
            listaProductos.postValue(productos)
        }
    }

    fun guardarProducto(producto: Producto) {
        viewModelScope.launch {
            repository.insertar(producto)
        }
    }

    fun actualizarProducto(producto: Producto) {
        viewModelScope.launch {
            repository.actualizar(producto)
        }
    }

    fun eliminarProducto(producto: Producto) {
        viewModelScope.launch {
            repository.eliminar(producto)
        }
    }
}

// Factoría para instanciar el ViewModel pasando el repositorio
class ProductoViewModelFactory(
    private val repository: ProductoRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductoViewModel(repository) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida")
    }
}