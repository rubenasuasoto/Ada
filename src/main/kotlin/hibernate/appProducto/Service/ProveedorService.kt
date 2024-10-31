package hibernate.appProducto.Service

import hibernate.appProducto.model.Proveedor
import hibernate.appProducto.repository.ProveedorRepository


class ProveedorService(val proveedorDao: ProveedorRepository) {

    fun GetProveedores() : List<Proveedor>{
        return proveedorDao.GetProveedores()
    }

    fun AltaProveedor(): Proveedor{

        println("Ingrese Nombre del Proveedor: ")
        val nombreProveedor = readln()
        println("Ingrese Dirección del Proveedor: ")
        val direccionProveedor = readln()

        val proveedor = Proveedor(
            nombre = nombreProveedor, direccion = direccionProveedor)

        proveedorDao.AltaProveedor(proveedor)

        return proveedor
    }
}