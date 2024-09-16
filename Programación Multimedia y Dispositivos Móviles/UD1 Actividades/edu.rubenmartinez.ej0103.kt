fun main() {
    print("Introduzca un numero del 1 al 7: ")
    var numero = readLine()!!.toInt()
    var dia: String
    when(numero){
        1 -> dia = "lunes"
        2 -> dia = "martes"
        3 -> dia = "miercoles"
        4 -> dia = "jueves"
        5 -> dia = "viernes"
        6,7 -> dia = "fin de semana"
        else -> dia = "incorrecto el dia introducido"
    }
    print("Es $dia")
}