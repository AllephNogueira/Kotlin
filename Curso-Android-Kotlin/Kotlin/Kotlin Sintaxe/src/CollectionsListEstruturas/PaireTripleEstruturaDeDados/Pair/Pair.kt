package CollectionsListEstruturas.PaireTripleEstruturaDeDados.Pair

/**
 * Sao duas estruturas de dados que, podemos trabalhar com 2 ou 3 valores.
 *
 * Pair - um par de valores
 * Triple - um trio de valores
 *
 *
 * Imagina que estamos trabalhando na uber e precisamos da localização do carro
 * Então podemos criar um pair (latitudeValor1, longitudeValor2)
 *
 * Ou então podemos fazer um triple(latitudeValor1, longitudeValor2, nomeMotoristaValor3)
 */

fun main() {

    val localizacao = Pair(10545, 456546546)
    println("Latitude: ${localizacao.first} - Longitude: ${localizacao.second}")

    // Outra maneira de usar o pair

    val localizacao2 = 123123 to 2131231
    println("Latitude: ${localizacao2.first} - Longitude: ${localizacao2.second}")
}

