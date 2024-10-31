package hibernate.appInstituto.repository

import hibernate.appInstituto.model.Departamento
import hibernate.appInstituto.repository.DepartamentoRepository.Companion.emf
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

class DepartamentoRepository {
    companion object {
         val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("unidad-MySQL")
    }

    fun insertDpto(dpto: Departamento){

        val em: EntityManager = emf.createEntityManager()
        try {
            em.transaction.begin()
            em.persist(dpto)
            em.transaction.commit()
        } catch (ex: Exception) {
            ex.printStackTrace()

                em.transaction.rollback()

        } finally {
            em.close()
        }
    }
    }
    fun findDpto():List<Departamento>{

        val em: EntityManager = emf.createEntityManager()
        val find = em.createQuery("SELECT d FROM Departamento d", Departamento::class.java).resultList
        em.close()
        return  find



    }
fun updateDpto(dpto: Departamento) {
    val em: EntityManager = emf.createEntityManager()
    try {
        em.transaction.begin()
        em.merge(dpto)
        em.transaction.commit()
    } catch (ex: Exception) {
        ex.printStackTrace()

            em.transaction.rollback()

    } finally {
        em.close()
    }
}
fun deleteDptoById(id: Long) {
    val em: EntityManager = emf.createEntityManager()
    try {
        em.transaction.begin()
        val dpto = em.find(Departamento::class.java, id)
        if (dpto != null) {
            em.remove(dpto)
            em.transaction.commit()
        } else {
            println("Departamento con ID $id no encontrado")
            em.transaction.rollback()
        }
    } catch (ex: Exception) {
        ex.printStackTrace()

            em.transaction.rollback()

    } finally {
        em.close()
    }
}

