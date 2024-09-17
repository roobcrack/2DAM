data class coche(var marca: String, var modelo: String, var anyo: String)

fun main() {
    var coche = coche("Mercedes","Benz","1999")
    var coche2 = coche("Hyundai","i20","2013")

    print("Los modelos de los coches son: ${coche.marca} y ${coche2.marca}")
}