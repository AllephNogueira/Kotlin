package ProgramacaoFuncional

// Funçao normal com parametro
fun somarParametro(a: Int, b:Int) : Int {
    return a+b
}

// Funçao inline com parametro
fun somarParametroInline(a: Int, b : Int) : Int = a+b

fun main() {
    val somar = somarParametro(b = 20, a = 10) // Mudamos a ordem e colocamos o nome do parametro
    println(somar) // Saida 30


    val somarInline = somarParametroInline(b = 20, a = 30) // Observa que mudamos a ordem e colocamos o nome do parametro
    println(somarInline) // Saida 50
}