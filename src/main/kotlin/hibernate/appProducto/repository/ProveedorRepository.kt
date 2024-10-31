package hibernate.appProducto.repository

import hibernate.appProducto.model.Proveedor
import jakarta.persistence.EntityManager


class ProveedorRepository{

    fun AltaProveedor(proveedor: Proveedor): String {
        val em: EntityManager = hibernate.appProducto.Utils.HibernateUtils.getEntityManager()
        try {
            em.transaction.begin()
            em.persist(proveedor)
            em.transaction.commit()
            return "Se ha dado de alta el producto ${proveedor.nombre}"
        }catch (e : Exception) {
            em.transaction.rollback()
            return ("Algo a salido mal : ${e.message}")
        }finally {
            em.close()
        }
    }

    fun GetProveedores() : List<Proveedor> {
        val em: EntityManager = hibernate.appProducto.Utils.HibernateUtils.getEntityManager()
        try {
            val query = "FROM Proveedor "
            val proveedorFromBD = em.createQuery(query, Proveedor::class.java).resultList
            return proveedorFromBD

        }catch (e : Exception) {
            println(e.message)
            return listOf()
        }
    }
}