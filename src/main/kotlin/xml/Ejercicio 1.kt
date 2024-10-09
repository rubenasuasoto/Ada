package xml

import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.File
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory

fun main() {
    val file = Path.of("src/main/resources/empleados.xml")
    procesarArchivoXML(file.toFile())

    val dbf = DocumentBuilderFactory.newInstance()
    val db = dbf.newDocumentBuilder()
    val ficheroXML = Path.of("src").resolve("main").resolve("resources").resolve("empleados.xml")
    val document = db.parse(ficheroXML.toFile())

    // 1 obtener root
    val root: Element = document.documentElement
    // normalizar el arbol
    root.normalize()
    //  para obtener elementos  por su nombre de etiqueta
    val listaNodos = root.getElementsByTagName("empleado")
    // cuando tenemos la nodelist , podemos iterar con ella

}
fun listaEmpleados(file: File){
    val dbFactory = DocumentBuilderFactory.newInstance()
    val dBuilder = dbFactory.newDocumentBuilder()
    val doc = dBuilder.parse(file)
    // Normalizamos el documento
    doc.documentElement.normalize()
    val listaNodos = doc.documentElement.getElementsByTagName("empleados")

    for (i in 0..< listaNodos.length ) {


        // para acceder a un item en particular
        val nodo: Node = listaNodos.item(i)

        // para acceder al tipo de nodo
        if (nodo.nodeType == Node.ELEMENT_NODE) {
            // casteamos
            val nodoElemento: Element = nodo as Element
            // podemos buscar los elementos que nos conviene
            val elementoSalario = nodoElemento.getElementsByTagName("salario")

            // una vez que tenemos el elemento que queremos podemos acceder a su contenido
            val textContentSalario = elementoSalario.item(0).textContent


            if(textContentSalario.toString() > 2000.0.toString()) {
                println("Empleado ${i}:\n\t - Salario: $textContentSalario")
            }else{

            }
        }
    }
}