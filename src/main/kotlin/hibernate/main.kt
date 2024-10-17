package hibernate

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

fun main(){

        val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("unidad-MySQL")
        val em: EntityManager = emf.createEntityManager()



        // si queremos empezar una transaccion
    em.transaction.begin()

    val e:Empleado = Empleado("124","pepe",39)
    em.persist(e)// .persist() persiste el objeto en un persistenceContext

    // Empujamos los cambios a la base de datos
    em.transaction.commit()

    // cerrar la conexion
    em.close()


}
