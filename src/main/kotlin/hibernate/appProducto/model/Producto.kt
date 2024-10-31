package hibernate.appProducto.model

import hibernate.appInstituto.model.Director
import jakarta.persistence.*
import java.util.*

@Entity
data class Producto(
    @Id
    val id: String = "",

    @Column(nullable = false, length = 10)
    var categoria: String = "",

    @Column(nullable = false, length = 50)
    var nombre: String = "",

    var descripcion: String? = null,

    @Column(nullable = false)
    var precioSinIva: Float =0.0f,

    @Column(nullable = false)
    var precioConIva: Float = precioSinIva * 1.21f,

    @Column(nullable = false)
    var fechaAlta: Date = Date(),

    @Column(nullable = false)
    var stock: Int = 0 ,
    var director: String = "",
    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    var proveedor: Proveedor? = null
) {

}
