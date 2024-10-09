package com.es.tema1.repositorio

import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.createFile
import kotlin.io.path.notExists

class Cotizacion {

    // Método para leer las cotizaciones  y almacenarlas en un mapa
    fun mostrar(ruta: Path): MutableMap<Int, List<String>> {

        val rutaInicial = ruta.resolve("cotizacion.csv")

        val br: BufferedReader = Files.newBufferedReader(rutaInicial)
        // Lee y descarta el encabezado
        br.readLine()

        // Mapa para almacenar las cotizaciones, donde la clave es un índice y el valor es una lista de cadenas
        val diccionario = mutableMapOf<Int, List<String>>()
        // Contador para las líneas leídas
        var numeros = 0

        // Crea el archivo si no existe
        if (rutaInicial.notExists()) {
            rutaInicial.createFile()
        }

        // Lee cada línea del archivo, la divide por ";" y la almacena en el mapa Datos
        br.use {
            it.forEachLine { line ->
                val todoSpliteado = line.split(";")
                diccionario[numeros] = todoSpliteado
                numeros++
            }
        }
        // Devuelve el mapa con las cotizaciones
        return diccionario
    }

    // Método para resumir las cotizaciones y escribir el resumen en el  archivo CSV
    fun resumir(ruta: Path, map: MutableMap<Int, List<String>>) {
        // Resuelve la ruta completa del archivo "resumen_cotizacion.csv"
        val rutafinal = ruta.resolve("resumen_cotizacion.csv")
        // Crea el archivo si no existe
        if (rutafinal.notExists()) {
            rutafinal.createFile()
        }

        // Contador para las líneas procesadas
        var numero = 0
        // Crea un BufferedWriter para escribir en el archivo
        val bw = Files.newBufferedWriter(rutafinal)

        // Itera sobre el mapa map y para cada entrada:
        bw.use {
            for (i in map) {
                // Obtiene y convierte el valor máximo de la cotización
                val max = map[numero]?.get(2)?.replace(".", "")?.replace(",", ".")?.toDouble() ?: 0.0
                // Obtiene y convierte el valor mínimo de la cotización
                val min = map[numero]?.get(3)?.replace(".", "")?.replace(",", ".")?.toDouble() ?: 0.0
                // Calcula la media de min y max
                val media = ((min + max) / 2).toString().format("%.2f").replace(",", ".")
                // Escribe la información formateada en el archivo
                it.write(
                    "Nombre : ${map[numero]?.get(0)}  |  maximo : $max  |  minimo : $min |  media : $media\n"
                )
                // Incrementa el contador para la siguiente línea
                numero++
            }
        }
    }
}
