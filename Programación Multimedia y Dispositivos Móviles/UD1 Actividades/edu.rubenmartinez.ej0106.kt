fun esPrimo(numero: Int): Boolean {
    if (numero <= 1) {
        return false
    }
    for (i in 2 until numero) {
        if (numero % i == 0) {
            return false
        }
    }
    return true
}

fun main() {
    val numero = readln()!!.toInt()

    if (esPrimo(numero)) {
        println("$numero es un número primo.")
    } else {
        println("$numero no es un número primo.")
    }
}
