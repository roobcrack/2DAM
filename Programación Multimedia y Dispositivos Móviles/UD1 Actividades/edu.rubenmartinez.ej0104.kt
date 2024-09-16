fun main() {
    print("Introduzca un numero: ")
    var numero = readLine()!!.toInt()
    for(i in 1..10){
        println("$i * $numero = ${i*numero}") }
}