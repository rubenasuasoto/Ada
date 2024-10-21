package hibernate.appInstituto

import jakarta.persistence.*

@Entity
@Table(name="proveedores")
data class Proveedor (

    @Column(name="cif", unique = true, nullable = false)
    val cif: String,

    @Column(nullable = false)
    val nombre: String,

    @ManyToMany(mappedBy = "proveedores", cascade = [CascadeType.ALL])
    val institutos: MutableList<Instituto>?,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,


    ) {

}