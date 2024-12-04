package ProgramacaoFuncional.FuncoesDeEscopo

/**
 * Ela nao introduz nenhuma nova capacidade tecnica (Ela não vai trazer novos recursos)
 * Objetivos: Deixa o codigo muito mais conciso e de facil leitura
 * São atalhos para codigos que ja escrevemos.
 *
 *  Let > com o let podemos verifcar se o item e nulo, se nao for podemos fazer algo.
 *  Run >
 *  With >
 *  Apply >
 *  Also >
 */

data class Produto (var nome: String, var preco: Double)

fun salvarProduto(produto: Produto){}

fun main() {

    // Imagina que temos um produto que queremos alterar depois
    // Dessa forma criamos um produto nullo
    var produto: Produto? = null

    // Usuário pode escolher ou não o produto
    produto = Produto("Notebook", 00.00)

    /**
     * Poderiamos fazer dessa forma, mas olha como o codigo fica grande
     *
     *     // Só vamos poder salvar o produto se ele não for nulo
     * //    if (produto != null) {
     * //        produto.preco = 300.00
     * //        salvarProduto(produto)
     * //    }
     */


    /**
     * Com a funçao let, vamos verificar se esse produto não e nulo.
     * Se ele não for nulo, vamos usar ele como parametro.
     */
    produto?.let { item ->
        item.nome = "Notebook Asus"
        item.preco = 3000.00
        salvarProduto(produto)
    }

    println(produto)

    /**
     * Agora imagina que queremos fazer a lambda com retorno, igual de uma funçao
     */

    val novoProduto = produto?.let { item ->
        item.nome = "Cancelado"
        item.preco = 00.00
        item // Aqui para retornar fazemos somente isso.
    }

    println(novoProduto)

}