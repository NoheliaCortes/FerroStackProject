package com.sistemas.ferrostackproject.ui.productos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sistemas.ferrostackproject.R
import com.sistemas.ferrostackproject.data.entity.Producto

class ProductoAdapter(
    private var listaProductos: List<Producto> = emptyList()
) : RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgProducto: ImageView = view.findViewById(R.id.imgProducto)
        val txtNombreProducto: TextView = view.findViewById(R.id.txtNombreProducto)
        val txtCategoriaProducto: TextView = view.findViewById(R.id.txtCategoriaProducto)
        val txtPrecioProducto: TextView = view.findViewById(R.id.txtPrecioProducto)
        val txtCantidadProducto: TextView = view.findViewById(R.id.txtCantidadProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = listaProductos[position]

        holder.txtNombreProducto.text = producto.nombre
        holder.txtCategoriaProducto.text = producto.categoria
        holder.txtPrecioProducto.text = "C$ ${producto.precio}"
        holder.txtCantidadProducto.text = "Stock: ${producto.cantidad}"

        // Si no tienes imagen en la entidad, puedes setear un icono por defecto:
        // holder.imgProducto.setImageResource(R.drawable.ic_producto_default)
    }

    override fun getItemCount(): Int = listaProductos.size

    fun actualizarLista(nuevaLista: List<Producto>) {
        listaProductos = nuevaLista
        notifyDataSetChanged()
    }
}