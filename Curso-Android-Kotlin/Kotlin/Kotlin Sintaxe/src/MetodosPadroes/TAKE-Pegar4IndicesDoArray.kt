package MetodosPadroes

/** Imagina que estamos com uma lista e quero pegar 4 itens dela */

fun main() {
    val listaArr = arrayOf(10,20,30,40,50,60,70,80,90,100,110,120,130,140,150)

    val quartroPrimeirosNumeros = listaArr.take(4)

    println(quartroPrimeirosNumeros)

    val quatroUltimosNumeros = listaArr.takeLast(4)

    println(quatroUltimosNumeros)

    // Agora imagina que queremos somar esses numeros

    val somaDoArray = listaArr.sum()

    println("$somaDoArray")
}