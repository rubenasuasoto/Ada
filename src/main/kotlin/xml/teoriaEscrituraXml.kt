package xml


import org.w3c.dom.DOMImplementation
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Text
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult


fun main(){

    /*
    * Escritura de archivos XML
    */

    val factory:DocumentBuilderFactory = DocumentBuilderFactory.newInstance()

    val builder: DocumentBuilder = factory.newDocumentBuilder()

    val imp: DOMImplementation = builder.domImplementation
    // opcional
    val document: Document = imp.createDocument(null,"productos", null)

    // En este punto, ya tendriamos el primer element creado. El nodo root
    // 3º Solo tenemos que adjuntar hijos al nodo root

    // a) Primeros, creamos ELEMENT
    val producto1: Element = document.createElement("producto")
    document.documentElement.appendChild(producto1)

    // b) Hijos de producto1
    val precioP1: Element = document.createElement("precio")
    val nameP1: Element = document.createElement("nombre")

    val textoNombreP1: Text = document.createTextNode("Agua")
    val textoPrecioP1: Text = document.createTextNode("1.50")

    //Unimos el textNode al elemento correspondiente
    nameP1.appendChild(textoNombreP1)
    precioP1.appendChild(textoPrecioP1)

    // Unimos el nombre y el precio al producto
    producto1.appendChild(nameP1)
    producto1.appendChild(precioP1)

    // Añadiriamos todos los nodos que quisieramos

    // 4º Procedemos a crear el XML
    // -> ¿Que queremos escribir?
    val source: Source = DOMSource(document)

    // -> Que clase usaremos para escribir: StreamResult(File)
    val result: StreamResult = StreamResult(Path.of("C:\\Users\\ruben\\OneDrive\\Escritorio\\Programaciòn\\Ada\\ADA_1\\src\\main\\resources\\producto.xml").toFile())

    // -> Que herramientas usamos para realizar la transformacion: Trasnformer
    val transformer: Transformer = TransformerFactory.newInstance().newTransformer()

    // BONUS POINT
    // Para indentar el XML correctamente
    transformer.setOutputProperty(OutputKeys.INDENT,"yes")

    //POR ULTIMO. Realizamos la transformacion
    transformer.transform(source,result)



}