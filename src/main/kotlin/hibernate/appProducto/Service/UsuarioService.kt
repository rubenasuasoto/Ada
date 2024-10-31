package hibernate.appProducto.Service

import hibernate.appProducto.model.Usuario
import hibernate.appProducto.repository.UsuarioRepository


class UsuarioService(val usuarioRepository: UsuarioRepository) {

    fun ComprobarUsuario(nombre: String, password: String): Usuario? {
        val usuarioBD = usuarioRepository.getUsuario(nombre)
        return if (usuarioBD != null && usuarioBD.password == password) {
            usuarioBD
        } else {
            null
        }
    }
}
