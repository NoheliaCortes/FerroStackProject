package com.sistemas.ferrostackproject.data.repository

import com.sistemas.ferrostackproject.data.dao.ProductoDao
import com.sistemas.ferrostackproject.data.entity.Producto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductoRepository(private val productoDao: ProductoDao) {

    suspend fun insertar(producto: Producto) = withContext(Dispatchers.IO) {
        productoDao.insertar(producto)
    }

    suspend fun actualizar(producto: Producto) = withContext(Dispatchers.IO) {
        productoDao.actualizar(producto)
    }

    suspend fun eliminar(producto: Producto) = withContext(Dispatchers.IO) {
        productoDao.eliminar(producto)
    }

    suspend fun obtenerTodos(): List<Producto> = withContext(Dispatchers.IO) {
        productoDao.obtenerTodos()
    }
}