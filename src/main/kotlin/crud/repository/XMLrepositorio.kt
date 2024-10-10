package crud.repository

import crud.model.Empleado
import org.w3c.dom.Document
import org.w3c.dom.Element
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
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


    }
    private fun parseXML(rutaXml: Path): Document {
        val dbf = DocumentBuilderFactory.newInstance()
        val db = dbf.newDocumentBuilder()

        return db.parse(rutaXml.toFile())
    }

}