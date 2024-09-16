fun main() {
    print("Escribe un numero: ")
    var n1 = readLine()!!.toInt()
    var par: String = if(n1%2==0) "par" else "impar"
    println("El numero es $par")
}