package crud

import crud.model.Empleado
import crud.repository.XMLrepositorio
import java.nio.file.Path

fun main(){

    val ficheroXML :Path = Path.of("src").resolve("main/resources/empleado.xml")
    val e: Empleado = Empleado("22","linares","IT","5000")
    XMLrepositorio().insert(e, ficheroXML)
    XMLrepositorio().delete("21",ficheroXML)

}