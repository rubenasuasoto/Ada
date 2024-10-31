package hibernate.appProducto.repository

import hibernate.appProducto.model.Producto
import hibernate.appProducto.model.Proveedor
import jakarta.persistence.EntityManager


class ProductoRepository {

    fun AltaProducto(producto: Producto): String {
        val em: EntityManager = hibernate.appProducto.Utils.HibernateUtils.getEntityManager()
        try {
            em.transaction.begin()
            em.persist(producto)
            em.transaction.commit()
            return "Se ha dado de alta el producto ${producto.nombre}"
        }catch (e : Exception) {
            em.transaction.rollback()
            return ("Algo a salido mal : ${e.message}")
        }finally {
            em.close()
        }
    }

    fun BajaProducto(producto_id: String): String {
        val em: EntityManager = hibernate.appProducto.Utils.HibernateUtils.getEntityManager()
        try {
            em.transaction.begin()
            val productoFromBD = em.find(Producto::class.java, producto_id)
            em.remove(productoFromBD)
            em.transaction.commit()
            return "Se ha dado de baja el producto ${producto_id}"

        }catch (e:Exception){
            em.transaction.rollback()
            return ("Algo a salido mal : ${e.message}")
        }finally {
            em.close()
        }
    }

    fun UpdateNombreProducto(producto_id: String , nuevoNombre:String):String {
        val em: EntityManager = hibernate.appProducto.Utils.HibernateUtils.getEntityManager()
        try {
            em.transaction.begin()
            val productoFromBD = em.find(Producto::class.java, producto_id)
            productoFromBD.nombre = nuevoNombre
            em.transaction.commit()
            return "Se ha actualizado el producto ${producto_id} al nombre ${nuevoNombre}"

        }catch (e: Exception){
            em.transaction.rollback()
            return ("Algo a salido mal : ${e.message}")
        }finally {
            em.close()
        }
    }

    fun UpdateStockProducto(producto_id: String , nuevoStock:Int):String {
        val em: EntityManager = hibernate.appProducto.Utils.HibernateUtils.getEntityManager()
        try {
            em.transaction.begin()
            val productoFromBD = em.find(Producto::class.java, producto_id)
            productoFromBD.stock = nuevoStock
            em.transaction.commit()
            return "Se ha actualizado el producto ${producto_id} con un nuevo stock ${nuevoStock}"

        }catch (e: Exception){
            em.transaction.rollback()
            return ("Algo a salido mal : ${e.message}")
        }finally {
            em.close()
        }
    }

    fun GetProducto(producto_id: String): Producto? {
        val em: EntityManager = hibernate.appProducto.Utils.HibernateUtils.getEntityManager()
        try {

            val productoFromBD = em.find(Producto::class.java, producto_id)
            return productoFromBD

        }catch (e: Exception){
            return null
        }finally {
            em.close()
        }
    }

    fun GetProductosConStock():List<Producto>{
        val em: EntityManager = hibernate.appProducto.Utils.HibernateUtils.getEntityManager()
        try {

            val query = "FROM Producto where stock>0 "
            val productosFromBD = em.createQuery(query, Producto::class.java).resultList
            return productosFromBD

        }catch (e : Exception) {
            println(e.message)
            return listOf()
        }
    }

    fun GetProductosSinStock():List<Producto>{
        val em: EntityManager = hibernate.appProducto.Utils.HibernateUtils.getEntityManager()
        try {

            val query = "FROM Producto where stock=0 "
            val productosFromBD = em.createQuery(query, Producto::class.java).resultList
            return productosFromBD

        }catch (e : Exception) {
            println(e.message)
            return listOf()
        }
    }

    fun GetProveedorDeProducto(producto_id: String): Proveedor? {
        val em: EntityManager =hibernate.appProducto.Utils.HibernateUtils.getEntityManager()
        try {

            val productoFromBD = em.find(Producto::class.java, producto_id)
            return productoFromBD.proveedor

        }catch (e: Exception){
            return null
        }finally {
            em.close()
        }
    }

}