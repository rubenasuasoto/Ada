package com.es.tema1


import com.es.tema1.repositorio.Cotizacion
import java.nio.file.Path
import kotlin.io.path.Path

/*
Nombre (nombre de la empresa), Final (precio de la acción al cierre de bolsa), Máximo (precio máximo de la acción durante la jornada), Mínimo (precio mínimo de la acción durante la jornada), Volumen (Volumen al cierre de bolsa), Efectivo (capitalización al cierre en miles de euros).
 */

fun main() {
    val cotiza = Cotizacion()
    val ruta = Path.of("src").resolve("main").resolve("resources")

    cotiza.resumir(ruta,cotiza.mostrar(ruta))

}