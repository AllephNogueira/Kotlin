package com.allephnogueira.sqliteandroid.database

import android.content.Context
import android.util.Log
import com.allephnogueira.sqliteandroid.model.Produto

class ProdutoDAO(context: Context) : IProdutoDAO {
    /** Essa classe vai ser responsavel por fazer o CRUD de PRODUTOS
     * Estamos encadeando os metodo para ser mais facil de usar depois, tanto escrita quanto leitura. **/

    val escrita = DatabaseHelper(context).writableDatabase // Esse metodo serve para escrita (UPDATE, DELETE, INSERT)
    val leitura = DatabaseHelper(context).readableDatabase // Esse metodo serve para leitura (SELECT)



    override fun salvar(produto: Produto): Boolean {

        val comandoSQL =
            "INSERT INTO ${DatabaseHelper.TABELA_PRODUTOS} (id_produto, titulo, descriacao) VALUES (null, '${produto.titulo}', 'Descrição...');"

        try {
            escrita.execSQL(comandoSQL) // writable para escrita
            Log.i("info_db", "Produto cadastrado com sucesso.")

        } catch (e: Exception) {
            e.printStackTrace()
            return false /** Se der tudo errado ele retorna um false **/
        }

        return true
    }

    override fun atualizar(produto: Produto): Boolean {
        /** Basicamente vamos atualizar o nome do produto onde o id seja igual a 1
         * Poderiamos deixar o usuario definir também qual vai ser o id, mas isso é mais para frente.
         */


        val codigoSQL = "UPDATE produtos SET titulo = '${produto.titulo}' WHERE id_produto = 2 ;"

        try {
            escrita.execSQL(codigoSQL)
            Log.i("info_db", "Sucesso ao atualizar")
        }catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_db", "Erro ao atualizar")
            return false
        }
        return true
    }

    override fun remover(idProduto: Int): Boolean {
        /** Remover um produto onde o ID seja igual a 01
         * Futuramente vamos fazer um recyclerView para poder remover um produto. **/


        val codigoSQL = "DELETE FROM produtos WHERE id_produto = $idProduto"

        try {
            escrita.execSQL(codigoSQL)
            Log.i("info_db", "Sucesso ao remover")
        }catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_db", "Erro ao remover")
            return false
        }
        return true
    }

    override fun listar(): List<Produto> {

        val listaProdutos = mutableListOf<Produto>() // Criando uma lista para salvar os produtos

        val codigoSQL = "SELECT * FROM ${DatabaseHelper.TABELA_PRODUTOS}"
        val cursor = leitura.rawQuery(codigoSQL, null)


        while ( cursor.moveToNext() ) {
            val idProduto = cursor.getInt(0)
            val titulo = cursor.getString(1)
            val descricao = cursor.getString(2)


            /** Resumindo vamos percorrer todo nosso banco de dados e todos os produtos que ele encontrar ele vai salvar dentro da nossa lista */

            listaProdutos.add( // Adicionando os produtos dentro da lista.
                Produto(idProduto, titulo, descricao)
            )

        }

        return listaProdutos // Retornando a lista de produtos

    }


}