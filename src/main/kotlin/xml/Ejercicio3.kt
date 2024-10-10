package xml
import org.w3c.dom.*
import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import kotlin.io.path.createFile
import kotlin.io.path.notExists

class Empresa {
    fun leerArchivoCSV(): MutableList<List<String>> {
        val ruta = Path.of("src/main/resources/empleados.csv")
        val empleadosList = mutableListOf<List<String>>()
        if (ruta.notExists()) {
            ruta.createFile()
        }
        val br = BufferedReader(Files.newBufferedReader(ruta))
        br.readLine()
        br.use { flujo ->
            flujo.forEachLine { line ->
                val empleadoData = line.replace("\t", ",").split(",")

                empleadosList.add(empleadoData)
            }
        }
        return empleadosList
    }

    fun crearXml(empleadosList: MutableList<List<String>>) {
        val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
        val builder: DocumentBuilder = factory.newDocumentBuilder()
        val imp: DOMImplementation = builder.domImplementation
        val document: Document = imp.createDocument(null, "empleados", null)


        for (empleadoData in empleadosList) {
            val empleado: Element = document.createElement("empleado")
            empleado.setAttribute("id", empleadoData[0])

            val apellido: Element = document.createElement("apellido")
            val departamento: Element = document.createElement("departamento")
            val salario: Element = document.createElement("salario")

            val textoApellido: Text = document.createTextNode(empleadoData[1])
            val textoDepartamento: Text = document.createTextNode(empleadoData[2])
            val textoSalario: Text = document.createTextNode(empleadoData[3])

            apellido.appendChild(textoApellido)
            departamento.appendChild(textoDepartamento)
            salario.appendChild(textoSalario)

            empleado.appendChild(apellido)
            empleado.appendChild(departamento)
            empleado.appendChild(salario)

            document.documentElement.appendChild(empleado)
        }

        val source: Source = DOMSource(document)
        val result = StreamResult(Path.of("src/main/resources/empleado.xml").toFile())

        val transformer: Transformer = TransformerFactory.newInstance().newTransformer()
        transformer.setOutputProperty(OutputKeys.INDENT, "yes")

        transformer.transform(source, result)
    }

    fun modificarXml(id: String, nuevoSalario: String) {
        val rutaXml = Path.of("src/main/resources/empleado.xml")
        val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
        val builder: DocumentBuilder = factory.newDocumentBuilder()
        val document: Document = builder.parse(Files.newInputStream(rutaXml))

        val empleados = document.getElementsByTagName("empleado")
        for (i in 0 until empleados.length) {
            val empleado = empleados.item(i) as Element
            if (empleado.getAttribute("id") == id) {
                val salario = empleado.getElementsByTagName("salario").item(0)
                salario.textContent = nuevoSalario
                break
            }
        }

        val source: Source = DOMSource(document)
        val result = StreamResult(rutaXml.toFile())
        val transformer: Transformer = TransformerFactory.newInstance().newTransformer()
        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.transform(source, result)
    }
    fun leerArchivoXml() {
        val dbf = DocumentBuilderFactory.newInstance()
        val db = dbf.newDocumentBuilder()
        val ficheroXML = Path.of("src/main/resources/empleado.xml")
        val document: Document = db.parse(ficheroXML.toFile())
        val root: Element = document.documentElement

        root.normalize()

        val listaNodos = root.getElementsByTagName("empleado")

        for (i in 0 until listaNodos.length) {
            val nodo: Node = listaNodos.item(i)

            if (nodo.nodeType == Node.ELEMENT_NODE) {
                val nodoElemento: Element = nodo as Element

                val atributoId = nodoElemento.getAttribute("id")


                val elementoApellido = nodoElemento.getElementsByTagName("apellido").item(0)?.textContent
                val elementoDepartamento = nodoElemento.getElementsByTagName("departamento").item(0)?.textContent
                val elementoSalario = nodoElemento.getElementsByTagName("salario").item(0)?.textContent

                println("ID: $atributoId, Apellido: $elementoApellido, Departamento: $elementoDepartamento, Salario: $elementoSalario")
            }
        }
    }
}

fun main() {
    val empresa = Empresa()

    val empleadosList = empresa.leerArchivoCSV()
    empresa.crearXml(empleadosList)


    empresa.modificarXml("2", "4500")
    empresa.leerArchivoXml()
}
