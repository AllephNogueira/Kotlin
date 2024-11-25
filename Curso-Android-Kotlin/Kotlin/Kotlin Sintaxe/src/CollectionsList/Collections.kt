package CollectionsList

/**
 * São usadas para armazenar lista de listas.
 *
 * Principais tipos de lista: List, Set, Map = Imutaveis (Não pode ser modificada)
 * * IMUTAVEL: Apos ser criada, nao podemos adicionar novos itens, nao podemos remover itens ou seja não podemos mexer no tamanho dela em geral.
 *
 * List = Uma lista parecida com Array
 * Set = Uma lista de itens unicos
 * Map = Map funciona como uma lista mas eu mesmo crio meus indices.
 *
 * * Transformando em mutavel
 * MutableList - MutableSet - MutableMap
 * Dessa forma podemos atualizar a lista.
 *
 * Metodos dentro do imutavel (List)
 * [] get, plus, size, indexOF, First, Last, Contains, Shuffled.
 *
 * Metodos dentro do MUTAVEL(Posso atualizar)
 * Todos os metodos antigos + add, Remove, Clear.
 *
 * O metodo MAP ele nao tem o metodo add, le tem o metodo PUT
 *
 * Atençao chaves são unicas, mas valores podem se repetir.
 *
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

}