package crud.model

data class Empleado(val id:String, val apellido:String,val dept:String, val salario:String){
    override fun toString(): String {
        return "Empleado(id='$id', apellido='$apellido', dept='$dept', salario='$salario')"
    }
}