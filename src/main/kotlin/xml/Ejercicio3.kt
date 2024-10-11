package xml
import java.io.BufferedReader
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.Text

class Empresa {
    fun leerArchivoCSV(): MutableList<List<String>> {
        val ruta = Path.of("src/main/resources/empleados.csv")
        val empleadosList = mutableListOf<List<String>>()
        try {
            if (Files.notExists(ruta)) {
                Files.createFile(ruta)
            }
            val br = BufferedReader(Files.newBufferedReader(ruta))
            br.readLine()
            br.use { flujo ->
                flujo.forEachLine { line ->
                    val empleadoData = line.replace("\t", ",").split(",")
                    empleadosList.add(empleadoData)
                }
            }
        } catch (e: IOException) {
            println("Error al leer el archivo CSV: ${e.message}")
        }
        return empleadosList
    }

    fun crearXml(empleadosList: MutableList<List<String>>) {
        try {
            val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
            val builder = factory.newDocumentBuilder()
            val imp = builder.domImplementation
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

            val source = DOMSource(document)
            val result = StreamResult(Path.of("src/main/resources/empleado.xml").toFile())

            val transformer = TransformerFactory.newInstance().newTransformer()
            transformer.setOutputProperty(OutputKeys.INDENT, "yes")

            transformer.transform(source, result)
        } catch (e: Exception) {
            println("Error al crear el archivo XML: ${e.message}")
        }
    }

    fun modificarXml(id: String, nuevoSalario: String) {
        try {
            val rutaXml = Path.of("src/main/resources/empleado.xml")
            val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
            val builder = factory.newDocumentBuilder()
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

            val source = DOMSource(document)
            val result = StreamResult(rutaXml.toFile())
            val transformer = TransformerFactory.newInstance().newTransformer()
            transformer.setOutputProperty(OutputKeys.INDENT, "yes")
            transformer.setOutputProperty(OutputKeys.METHOD, "xml")
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no")

            transformer.transform(source, result)
        } catch (e: Exception) {
            println("Error al modificar el archivo XML: ${e.message}")
        }
    }

    fun leerArchivoXml() {
        try {
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
        } catch (e: Exception) {
            println("Error al leer el archivo XML: ${e.message}")
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
