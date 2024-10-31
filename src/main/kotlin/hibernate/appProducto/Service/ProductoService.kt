package hibernate.appProducto.Service

import hibernate.appProducto.model.Producto
import hibernate.appProducto.model.Proveedor
import hibernate.appProducto.repository.ProductoRepository
import java.util.*

class ProductoService(val productoDao: ProductoRepository) {

    fun AltaProducto(proveedor: Proveedor){
        println("Ingrese Nombre del Producto: ")
        val nombreProducto = readln()
        println("Ingrese Categoria del Producto: ")
        val categoriaProducto = readln()
        println("Ingrese Precio sin IVA: ")
        val precioSinIva = readln().toFloatOrNull() ?: return
        println("Ingrese Descripción del Producto: ")
        val descripcionProducto = readln()
        println("Ingrese Stock del Producto: ")

        val stockProducto = readln().toIntOrNull() ?: return
        val precioConIva = (precioSinIva + (precioSinIva * 0.21)).toFloat()
        val fechaProducto = Date()
        val idProducto = HacerID(nombreProducto,categoriaProducto,proveedor.nombre)

        val producto = Producto(idProducto,categoriaProducto,nombreProducto,descripcionProducto,precioSinIva,precioConIva,fechaProducto,stockProducto,
            proveedor.toString()
        )

        productoDao.AltaProducto(producto)
    }

    fun BajaProducto(): String{
        println("Ingrese ID del Producto a eliminar: ")
        val id = readln()

        return productoDao.BajaProducto(id)
    }

    fun  UpdateNombreProducto():String{
        println("Ingrese ID del Producto a modificar: ")
        val producto_id = readln()
        println("Ingrese el nuevo nombre: ")
        val nuevoNombre = readln()

        return productoDao.UpdateNombreProducto(producto_id,nuevoNombre)
    }

    fun UpdateStockProducto():String{
        println("Ingrese ID del Producto a modificar: ")
        val producto_id = readln()
        println("Ingrese el nuevo stock: ")
        val nuevoStock = readln().toIntOrNull() ?: 0

        return productoDao.UpdateStockProducto(producto_id,nuevoStock)
    }

    fun GetProducto(): String {
        println("Ingrese ID del Producto a obtener: ")
        val producto_id = readln()
        val producto = productoDao.GetProducto(producto_id)
        if (producto == null) {
            return ("No se ha encontrado el producto.")
        }else{
            return ("${producto.nombre}  descripción : ${producto.descripcion} , precio : ${producto.precioConIva}")
        }
    }

    fun GetProductosConStock():List<Producto>{
        return productoDao.GetProductosConStock()
    }

    fun GetProductosSinStock():List<Producto>{
        return productoDao.GetProductosSinStock()
    }

    fun GetProveedorDeProducto(): String {
        println("Ingrese ID del Producto para obtener su proveedor: ")
        val producto_id = readln()

        val proveedor = productoDao.GetProveedorDeProducto(producto_id)

        if (proveedor == null) {
            return ("No se ha encontrado ningun producto.")
        }else{
            return ("${proveedor.nombre} con direccion : ${proveedor.direccion} e id : ${proveedor.id}")
        }
    }

    fun HacerID(nombreProducto:String , categoria:String,nombreProveedor:String):String{
        var idProducto = ""
        val posibilidadesId = listOf(nombreProducto.trim(),categoria.trim(),nombreProveedor.trim())

        for (i in 0..2){
            for (j in 0..2){
                idProducto += posibilidadesId[i][j]
            }
        }

        idProducto = idProducto.toUpperCase()

        return idProducto
    }
}