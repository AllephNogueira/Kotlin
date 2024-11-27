package CollectionsListEstruturas.Map

/**
 * Map são muito uteis quando queremos adicionar CHAVE e VALOR
 * Exemplo: ID 50 - Banana
 * Login : Alleph - senha123
 * Repara que podemos adicionar uma chave para aquele dado e o valor para ele.
 */



fun main() {
    // Repara que estamos criando a nossa chave 0
    // (to) para valor "Alleph"
    val listaUsuarios = mapOf(0 to "Alleph", 1 to "Fernanda")

    // Agora vamos imaginar que estamos criando um sistema de produtos
    val listaProdutos = mapOf(1055846 to "Ração Pedigree", 546489 to "Ração Whiskas")

    // Agora vamos imaginar um sistema de login
    val loginUsuario = mapOf("alleph" to "senha123", "fernanda" to "21611579")

    // exibindo e pegando esses dados.

    listaUsuarios.forEach {
            usuario ->
        println("Usuario: chave ${usuario.key} - valor ${usuario.value}")
    }

    /** Agora vamos acessar apenas um produto da lista
     * Imagina que temos um scanner que vai ler o produto
     */
    val produtoScameado = 1055846
    println("Produto scaneado ${listaProdutos.get(produtoScameado)}")


    // Agora vamos fazer um map mutavel onde podemos adicionar novos valores.

    val meuMapMutable = mutableMapOf("155554" to "Arroz Prato Fino")
}