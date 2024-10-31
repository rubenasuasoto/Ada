package hibernate.appInstituto.model

import jakarta.persistence.*

@Entity
@Table(name="proveedores")
data class Proveedor (


    @Column(nullable = false)
    val nombre: String,

    @ManyToMany(mappedBy = "proveedores", cascade = [CascadeType.ALL])
    val institutos: MutableList<Instituto>?,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,


    ) {

}