package CollectionsList.Metodos.union

/**
 * Imagina que temos LIST 1
 *      Hamburguer - Batata-Fritas
 *
 *  LIST 2
 *      Frango - Arroz
 *
 * E ai podemos fazer a union dessas duas listas e ter
 *
 *      Hamburguer - Batata-Fritas - Frango - Arroz
 *
 * Ou seja uma lista só juntando todos esses itens
 */

fun main() {
    val lanches = listOf("Hamburguer", "Batata-Fritas")
    val sobremesas = listOf("Casquinha", "Refrigerante")

    val pedidoCompleto = lanches.union(sobremesas)
//    val pedidoCompleto = lanches + sobremesas   // Outra forma de fazer

    println(pedidoCompleto)

    // Agora vamos imaginar que queremos remover algum item
    /**
     * Atenção: Na lista exclusão removemos apenas o batata fritas, mas poderiamos remover varios itens.
     */
    val lanchesExclusao = listOf("Batata-Fritas") // Item que vai ser excluido
    val novaLista = pedidoCompleto - lanchesExclusao // Criando uma nova lista, onde vamos receber a uniao das duas listas e remover o item excluido.
    println(novaLista)


}