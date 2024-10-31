package hibernate.appProducto.Utils

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

object HibernateUtils {

    private lateinit var emf: EntityManagerFactory

    private fun getEntityManagerFactory(namePersistenceUnit: String = "unidad-MySQL"): EntityManagerFactory {
        return if(this::emf.isInitialized && emf.isOpen) {
            emf;
        } else {
            Persistence.createEntityManagerFactory(namePersistenceUnit)
        }
    }

    fun getEntityManager(namePersistenceUnit: String = "unidad-MySQL"): EntityManager {
        return getEntityManagerFactory(namePersistenceUnit).createEntityManager()
    }


    // Método para cerrar todos los EntityManagerFactory
    fun shutdown() {
        if (emf.isOpen) {
            emf.close()
        }
    }

    // Método para cerrar un EntityManager específico
    fun closeEntityManager(em: EntityManager?) {
        try {
            if (em != null && em.isOpen) {
                em.close()
            }
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }



}