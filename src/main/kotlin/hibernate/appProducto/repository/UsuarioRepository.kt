package hibernate.appProducto.repository

import hibernate.appProducto.model.Usuario
import jakarta.persistence.EntityManager


class UsuarioRepository() {


    fun getUsuario(nombre: String) : Usuario? {
        val em: EntityManager =hibernate.appProducto.Utils.HibernateUtils.getEntityManager()
        try {
            val usuarioFromBD = em.find(Usuario::class.java, nombre)
            return usuarioFromBD

        }catch (e : Exception) {
            println(e.message)
            return null
        }finally {
            em.close()
        }
        
    }


}