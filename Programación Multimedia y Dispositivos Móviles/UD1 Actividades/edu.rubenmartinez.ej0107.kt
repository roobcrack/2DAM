fun main() {
    val dias= arrayOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo")
    print("Valores aceptados: ")
    for((posicion, valor) in dias.withIndex())
        print("${posicion+1} = $valor ")
    print("\nIntroduce un numero del 1 al 7: ")
    var numero: Int = readln()!!.toInt();
    if (numero in 1..7)
        println("Es el ${dias[numero-1]}")
    else
        println("Numero no valido");
}