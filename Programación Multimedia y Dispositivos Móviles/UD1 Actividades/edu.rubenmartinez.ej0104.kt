fun main() {
    print("Introduce a number: ")
    val number= readln()!!.toInt()
    for(i in 1..10){
        println("$i * $number = ${i*number}") }
}