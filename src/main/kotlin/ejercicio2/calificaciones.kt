package ejercicio2

import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Path
import java.util.Spliterator
import kotlin.io.path.createFile
import kotlin.io.path.notExists

class Calificaciones {
    /* Una función que reciba el fichero de calificaciones y devuelva
una lista de diccionarios, donde cada diccionario contiene la información de los
 exámenes y la asistencia de un alumno. La lista tiene que estar ordenada por apellidos.

*/
    fun mostrar(ruta: Path): MutableList<MutableMap<String, String?>> {

        val rutaInicial = ruta.resolve("calificaciones.csv")

        val br: BufferedReader = Files.newBufferedReader(rutaInicial)
        // Lee y descarta el encabezado
        br.readLine()


        val diccionario = mutableListOf<MutableMap<String, String?>>()


        // Crea el archivo si no existe
        if (rutaInicial.notExists()) {
            rutaInicial.createFile()
        }


        br.use {
            it.forEachLine { line ->
                val todoSpliteado = line.split(";")
                val map = mutableMapOf(
                    Pair(first = "Apellido", second = todoSpliteado[0]),
                    Pair("Nombre", todoSpliteado[1]),
                    Pair("Asistencia", todoSpliteado[2].replace("%", "")),
                    Pair("Parcial1", todoSpliteado[3].replace(",", ".")),
                    Pair("Parcial2", todoSpliteado[4].replace(",", ".")),
                    Pair("Ordinario1", todoSpliteado[5].replace(",", ".")),
                    Pair("Ordinario2", todoSpliteado[6].replace(",", ".")),
                    Pair("Practicas", todoSpliteado[7].replace(",", ".")),
                    Pair("OrdinarioPracticas", todoSpliteado[8].replace(",", ".")),
                    Pair("Nota final",calcularNotaFinal(todoSpliteado))
                )
                println(map)

                //Diccionario.add(map)

            }
        }

        // Devuelve el mapa
        return diccionario
    }


    private fun calcularNotaFinal(todoSpliteado : List<String>):String {

        var notafinal=0.0

        // primer parcial
        if((todoSpliteado[3].toDoubleOrNull()?: 0.0) > (todoSpliteado[5].toDoubleOrNull() ?: 0.0)){
            notafinal+= (todoSpliteado[3].toDoubleOrNull() ?: 0.0)*0.3
        }else {
            notafinal+= (todoSpliteado[5].toDoubleOrNull() ?: 0.0)*0.3

        }
        // segundo parcial
        if((todoSpliteado[4].toDoubleOrNull()?: 0.0) > (todoSpliteado[6].toDoubleOrNull() ?: 0.0)){
            notafinal+= (todoSpliteado[4].toDoubleOrNull() ?: 0.0)*0.3
        }else {
            notafinal+= (todoSpliteado[6].toDoubleOrNull() ?: 0.0)*0.3

        }
        // examen practica
        if((todoSpliteado[7].toDoubleOrNull()?: 0.0) > (todoSpliteado[8].toDoubleOrNull() ?: 0.0)){
            notafinal+= (todoSpliteado[7].toDoubleOrNull() ?: 0.0)*0.4
        }else {
            notafinal+= (todoSpliteado[8].toDoubleOrNull() ?: 0.0)*0.4

        }

        return String.format("%.2f", notafinal)
    }
    fun aprobadosYsuspensos(ruta: Path, map: List<MutableMap<String, String?>>) {


        val aprobado: MutableList<MutableMap<String, String?>> = mutableListOf()
        val suspenso: MutableList<MutableMap<String, String?>> = mutableListOf()

        for (alumno in map) {
            if ((alumno["Nota final"]?.replace(",", ".")?.toDouble())!! >= 5.0 && alumno["Asistencia"]!!.toInt() >= 75
            ) {
                aprobado.add(alumno)
            } else {
                suspenso.add(alumno)
            }
        }

    }
}
fun main() {
    val calificaciones = Calificaciones()
    val ruta = Path.of("src").resolve("main").resolve("resources")
    calificaciones.mostrar(ruta)


}