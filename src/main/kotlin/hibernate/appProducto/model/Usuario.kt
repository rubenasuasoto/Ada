package hibernate.appProducto.model

import jakarta.persistence.*

@Entity
@Table(name="Usuarios")
class Usuario(

    @Id
    @Column(name="Nombre")
    val nombre: String = "",
    @Column(name="password", nullable = false, length = 20)
    val password: String = "",



    )