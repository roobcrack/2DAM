fun main() {
    val lista MutableListString = mutableListOf()
    do{
        val texto String = readln()!!.toString()
        if(texto!=fin)
            lista.add(texto)
    }while(texto != fin)

    println(Primero la mostramos al reves)
    for(i in lista.size-1 downTo 0)
        println(lista[i])

    println(Segundo la imprimimos normal con foreach)
    lista.forEach { println(it) }
}