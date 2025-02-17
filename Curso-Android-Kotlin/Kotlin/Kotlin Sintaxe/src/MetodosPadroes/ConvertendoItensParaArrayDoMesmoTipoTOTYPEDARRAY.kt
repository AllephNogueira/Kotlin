package MetodosPadroes

/* Esse metodo é conhecido para transformar dados em um mesmo tipo em um array
Nesse exemplo vamos pegar um texto e vamos transformar ele em um array de Strings
 */

fun main() {
    val texto = "Eu quero, ir, a casa da minha mae, com, meu, irmao."

    // ONDE TIVER, VAMOS SEPARAR PARA UM NOVO INDICE
    // Transformando em um array do mesmo tipo: STRING

    val textoToTypedArray = texto.split(",").toTypedArray()

    // Exibindo a quantidade de itens no array

    println(textoToTypedArray.size)

    // Verificando para ver se o usuario digitou um campo em null
    val campoDigitado = 5
    if (textoToTypedArray[campoDigitado].isNotEmpty()) {
        println(textoToTypedArray[campoDigitado])
    } else {
        println("Não tem esse indice no array.")
    }


}