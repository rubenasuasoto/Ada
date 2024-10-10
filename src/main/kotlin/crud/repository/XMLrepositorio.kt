package crud.repository

import crud.model.Empleado
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Text
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import kotlin.io.path.notExists

class XMLrepositorio {

    // C -> Create / insertar
    fun insert(newEmpleado: Empleado,rutaXml: Path){
        if(rutaXml.notExists()){
            throw Exception("Ruta Xml no existe")
        }
        if (newEmpleado.id.isNullOrBlank()||
            newEmpleado.apellido.isNullOrBlank()||
            newEmpleado.dept.isNullOrBlank()){
            throw Exception("Atributos correctos ")

        }
        val document = parseXML(rutaXml)
        val root: Element = document.documentElement
        root.normalize()
        // insertar empleado
        val elementoNuevoEmpleado : Element = document.createElement("empleado")
        elementoNuevoEmpleado.setAttribute("id",newEmpleado.id)
        root.appendChild(elementoNuevoEmpleado)

        val apellido: Element = document.createElement("apellido")
        val departamento: Element = document.createElement("departamento")
        val salario: Element = document.createElement("salario")

        val textoApellido: Text = document.createTextNode(newEmpleado.apellido)
        val textoDepartamento: Text = document.createTextNode(newEmpleado.dept)
        val textoSalario: Text = document.createTextNode(newEmpleado.salario)
        apellido.appendChild(textoApellido)
        departamento.appendChild(textoDepartamento)
        salario.appendChild(textoSalario)

        elementoNuevoEmpleado.appendChild(apellido)
        elementoNuevoEmpleado.appendChild(departamento)
        elementoNuevoEmpleado.appendChild(salario)
        document.documentElement.appendChild(elementoNuevoEmpleado)
        val source: Source = DOMSource(document)
        val result = StreamResult(Path.of("src/main/resources/empleado.xml").toFile())

        val transformer: Transformer = TransformerFactory.newInstance().newTransformer()
        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.setOutputProperty(OutputKeys.METHOD, "yes")
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes")

        transformer.transform(source, result)
    }
    private fun parseXML(rutaXml: Path): Document {
        val dbf = DocumentBuilderFactory.newInstance()
        val db = dbf.newDocumentBuilder()

        return db.parse(rutaXml.toFile())
    }

}