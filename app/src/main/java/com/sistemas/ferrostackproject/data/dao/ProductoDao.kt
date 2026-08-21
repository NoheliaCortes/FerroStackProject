package com.sistemas.ferrostackproject.data.dao

import androidx.room.*
import com.sistemas.ferrostackproject.data.entity.Producto

@Dao
interface ProductoDao {

    @Insert
    fun insertar(producto: Producto)

    @Update
    fun actualizar(producto: Producto)

    @Delete
    fun eliminar(producto: Producto)

    @Query("SELECT * FROM productos ORDER BY id DESC")
    fun obtenerTodos(): List<Producto>
}