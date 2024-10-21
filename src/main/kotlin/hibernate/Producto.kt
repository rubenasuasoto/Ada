package hibernate

import hibernate.appInstituto.Proveedor
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name="Productos")
class Producto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null,

    @Column(name="Nombre")
    val nombre: String,

    @Column(name="Descripcion")
    val descripcion: String,
    @Column
    val precio: Double,
    @Column(name = "fecha_Alta")
    @Temporal(TemporalType.DATE)
    val fechaAlta: Date,
    @ManyToOne
    val proveedor: Proveedor?

    )