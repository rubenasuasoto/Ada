package hibernate.appInstituto

import hibernate.Producto
import hibernate.Usuario
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import java.time.Instant
import java.util.*

fun main(){

        val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("unidad-MySQL")
        val em: EntityManager = emf.createEntityManager()



    val dptos = mutableListOf<Departamento>()
    dptos.add(Departamento("Informatica", "Informatica", "Departamento de informatica", null))
    val inspectores = mutableListOf<Inspector>()
    inspectores.add(Inspector(null, "2345d", "pepe", null))

    val director: Director = Director(null, "123234z", "Paco", null)

    val proveedores = mutableListOf<Proveedor>()
    proveedores.add(Proveedor("null", "C1234", "Repuestos Suarez", null))

    val instituto: Instituto = Instituto(null, "Ies Rafael", "Call Amiel", 0, dptos, inspectores, director, proveedores)

    em.transaction.begin()

    em.persist(instituto)

    em.transaction.commit()

    em.close()
    emf.close()

}
