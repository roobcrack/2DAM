class Libro {
    var autor: String = "Anónimo"
    var titulo: String = "No indicado"
    var año: Int = -1

    fun obtenerDatos(): String {
        return "$titulo – $autor – $año"
    }
}

fun main() {
    val libro = Libro()
    libro.titulo = "It"
    libro.autor = "Stephen King"
    libro.año = 1986
    println(libro.obtenerDatos())
}
