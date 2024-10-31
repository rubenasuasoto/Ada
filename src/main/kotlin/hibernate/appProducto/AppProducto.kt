package hibernate.appProducto

import hibernate.appProducto.Service.ProductoService
import hibernate.appProducto.Service.ProveedorService
import hibernate.appProducto.Service.UsuarioService
import hibernate.appProducto.repository.ProductoRepository
import hibernate.appProducto.repository.ProveedorRepository
import hibernate.appProducto.repository.UsuarioRepository
import kotlin.system.exitProcess

fun main() {
    var login = false  // Variable para verificar el estado de login

    do {
        println(
            """
            ******************************************************
            ****    Bienvenid@ a StockControl               ******
            ******************************************************
                            
            Introduzca su usuario y contraseña para continuar (0 para salir)
            """.trimIndent()
        )
        print("Usuario: ")
        val userInput = readln()

        if (userInput.equals("0", ignoreCase = true)) {
            println("Saliendo...")
            exitProcess(0)
        } else {
            print("Contraseña: ")
            val passwordInput = readln()

            val usuarioService = UsuarioService(UsuarioRepository())
            val usuarioAutenticado = usuarioService.ComprobarUsuario(userInput, passwordInput)

            if (usuarioAutenticado != null) {
                println("Bienvenid@, ${usuarioAutenticado.nombre}")
                login = true
            } else {
                println("Error en el login: Usuario o contraseña incorrecta")
            }
        }
    } while (!login)



    // Stock management section

    var opcion: String
    do {
        println(
            """
            ******************************************************
            ****            APP STOCK CONTROL               ******
            ******************************************************
                            
            1. Alta producto
            2. Baja producto
            3. Modificar nombre producto
            4. Modificar stock producto
            5. Get producto por id
            6. Get productos con stock
            7. Get productos sin stock
            8. Get proveedores de un producto
            9. Get todos los proveedores
            0. Salir
            """.trimIndent()
        )
        print("Seleccione una opción: ")
        opcion = readln()

        try {
            when (opcion) {
                "1" -> altaProducto()
                "2" -> bajaProducto()
                "3" -> modificarNombreProducto()
                "4" -> modificarStockProducto()
                "5" -> getProductoPorId()
                "6" -> getProductosConStock()
                "7" -> getProductosSinStock()
                "8" -> getProveedoresDeUnProducto()
                "9" -> getTodosLosProveedores()
                "0" -> println("Saliendo...")
                else -> println("Error en la elección")
            }
        } catch (e: Exception) {
            println("ERROR CONTROLADO")
        }
    } while (opcion != "0")
}

fun altaProducto() {

    val productoService = ProductoService(ProductoRepository())
    val proveedorService = ProveedorService(ProveedorRepository())
    // Primero, llama a AltaProveedor para  la creación del proveedor
    val proveedor = proveedorService.AltaProveedor()

    // Luego, llama a AltaProducto, que recopilará datos del producto y usará el proveedor ya creado
    productoService.AltaProducto(proveedor)


}

fun bajaProducto() {

    val productoService = ProductoService(ProductoRepository())

    productoService.BajaProducto()
}

fun modificarNombreProducto() {

    val productoService = ProductoService(ProductoRepository())

    productoService.UpdateNombreProducto()
}

fun modificarStockProducto() {

    val productoService = ProductoService(ProductoRepository())
    productoService.UpdateStockProducto()

}

fun getProductoPorId() {

    val productoService = ProductoService(ProductoRepository())
    productoService.GetProducto()

}

fun getProductosConStock() {
    val productoService = ProductoService(ProductoRepository())

    productoService.GetProductosConStock()

}

fun getProductosSinStock() {
    val productoService = ProductoService(ProductoRepository())

        productoService.GetProductosSinStock()

}

fun getProveedoresDeUnProducto() {

    val productoService = ProductoService(ProductoRepository())
    productoService.GetProveedorDeProducto()
}

fun getTodosLosProveedores() {
    val proveedorService = ProveedorService(ProveedorRepository())
    proveedorService.GetProveedores()

}
