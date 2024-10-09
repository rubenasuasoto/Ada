package xml

import org.w3c.dom.Element
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory

fun main() {

    // Lectura de fichero XML

    // El objetivo es parsear un fichero xml a Arbol DOM
    // 1º seria instanciar un objeto DocumentBuilderFactory
    val dbf = DocumentBuilderFactory.newInstance()
    // 2º Con el dbf , podemos crear un objeto de tipo documentBuilder
    val db = dbf.newDocumentBuilder()
    // parseamos
    val ficheroXML = java.nio.file.Path.of("src").resolve("/main/resources/producto.xml")

    val document = db.parse(ficheroXML.toFile())

    // 1 obtener root
    val root: Element = document.documentElement
    // normalizar el arbol
    root.normalize()
    //  para obtener elementos  por su nombre de etiqueta
    val listaNodos = root.getElementsByTagName("producto")
    // cuando tenemos la nodelist , podemos iterar con ella
    for (i in 0..< listaNodos.length ) {


        // para acceder a un item en particular
        val nodo: Node = listaNodos.item(i)

        // para acceder al tipo de nodo
        if (nodo.nodeType == Node.ELEMENT_NODE) {
            // casteamos
            val nodoElemento: Element = nodo as Element
            // podemos buscar los elementos que nos conviene
            val elementoNombre = nodoElemento.getElementsByTagName("nombre")
            val elementoPrecio = nodoElemento.getElementsByTagName("precio")
            // una vez que tenemos el elemento que queremos podemos acceder a su contenido
            val textContentNombre  = elementoNombre.item(0).textContent
            val textContentPrecio  = elementoPrecio.item(0).textContent

            // imprimo
            println("Producto ${i}:\n\t - nombre: $textContentNombre\n\t - precio: $textContentPrecio")
        }
    }
}