package CollectionsListEstruturas.Map

/**
 * Vamos imaginar que queremos um map com 2 valores
 * Porque vamos fazer um sistema de leitura e exibir os dados.
 */

fun main() {
    val produtosMercado = mapOf(
        10 to Pair ("Ração pedigre" , 20.00),
        20 to Pair ("Ração Whikas", 35.00),
        4545 to Pair ("Ração Whikas Filhote", 45.00))

    // Agora acessando esses valores

    println("Digte o codigo do produto")
    val codigoProduto = readLine()?.toIntOrNull() // Imagina um scanner vai ler o codigo de barras e vai adicionar ele aqui.

    if (codigoProduto != null) { // Verifcar realmente se foi escaneado algum codigo
        val produto = produtosMercado.get(codigoProduto)
        if (produto != null) { // Agora vamos jogar o item dentro de produto
            println("Produto: ${produto.first} - ${produto.second}R$")
        }
    } else {
        println("Codigo não escaneado.")
    }



}