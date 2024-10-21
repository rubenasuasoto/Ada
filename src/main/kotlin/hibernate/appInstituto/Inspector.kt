package hibernate.appInstituto

import jakarta.persistence.*

@Entity
@Table(name = "inspectores")
class Inspector(

    @Column(name = "dni", unique = true, nullable = false)
    val dni: String,

    @Column(name = "nombre")
    val nombre: String,

    @OneToMany(cascade = [CascadeType.ALL])
    val instituto: Instituto?,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,



    ) {
}