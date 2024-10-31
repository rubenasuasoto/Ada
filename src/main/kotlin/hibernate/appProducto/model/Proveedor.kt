package hibernate.appProducto.model


import jakarta.persistence.*
import kotlin.collections.List

@Entity
data class Proveedor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true, nullable = false, length = 50)
    var nombre: String = "",

    @Column(nullable = false)
    var direccion: String = "",

    @OneToMany(mappedBy = "proveedor", cascade = [CascadeType.ALL])
    var productos: List<Producto>? = null
)
