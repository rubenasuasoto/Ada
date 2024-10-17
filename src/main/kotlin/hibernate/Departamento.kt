package hibernate

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name="departamentos")
class Departamento(


    @Column(name = "nombre", nullable = false, unique = true)
    val nombre:String,
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    val fechaCreacion:Date,

    @OneToOne(mappedBy = "departamento")
    val  empleado: Empleado,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val numDpto: Long=0
) {


}