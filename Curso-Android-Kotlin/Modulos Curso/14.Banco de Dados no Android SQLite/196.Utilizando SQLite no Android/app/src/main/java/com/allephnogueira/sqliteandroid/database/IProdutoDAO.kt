package com.allephnogueira.sqliteandroid.database

import com.allephnogueira.sqliteandroid.model.Produto

interface IProdutoDAO {

    /** Estamos passando um produto inteiro para quase todos os metodos, menos para o remover pq só  vamos precisar do ID
     * O Listar algo não vamos precisar passar parametros, apenas ele vai retornar uma Lista de produto. */

    fun salvar(produto: Produto) : Boolean
    fun atualizar(produto: Produto) : Boolean
    fun remover(idProduto: Int) : Boolean
    fun listar() : List<Produto>


}