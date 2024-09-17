fun main() {
    print("Introduce a text: ")
    val text= readln()!!.toString()
    for(letter in text)
        print("$letter ")
}