package hibernate

import jakarta.persistence.*

@Entity
@Table(name="Empleados")
class Empleado(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id :Long,

    @Column(name="Nombre")
    val nombre: String,

    @Column
    val edad: Int,
    @OneToOne
    @JoinColumn(name = "id_departamento")
    val departamento: Departamento

)