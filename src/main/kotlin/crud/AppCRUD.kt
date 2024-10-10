package crud

import crud.model.Empleado
import java.nio.file.Path

fun main(){

    val ficheroXML :Path = Path.of("src").resolve("main/resources/empleado.xml")
    val e:Empleado = Empleado("16","linares","IT","5000")
}