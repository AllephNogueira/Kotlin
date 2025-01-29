package com.allephnogueira.sqliteandroid.database

import android.content.ContentValues
import android.content.Context
import android.provider.ContactsContract.Data
import android.util.Log
import com.allephnogueira.sqliteandroid.model.Produto

class ProdutoDAO(context: Context) : IProdutoDAO {
    /** Essa classe vai ser responsavel por fazer o CRUD de PRODUTOS
     * Estamos encadeando os metodo para ser mais facil de usar depois, tanto escrita quanto leitura. **/

    private val escrita = DatabaseHelper(context).writableDatabase // Esse metodo serve para escrita (UPDATE, DELETE, INSERT)
    private val leitura = DatabaseHelper(context).readableDatabase // Esse metodo serve para leitura (SELECT)



    override fun salvar(produto: Produto): Boolean {

        //val comandoSQL = "INSERT INTO ${DatabaseHelper.TABELA_PRODUTOS} (id_produto, titulo, descriacao) VALUES (null, '${produto.titulo}', 'Descrição...');"


        val valores = ContentValues()
        // Aqui seria o nome da coluna e o valor.
        valores.put("${DatabaseHelper.TITULO}", produto.titulo)
        // Imagina que temos mais de um valor, então podemos fazer mais chaves e valores
        valores.put("${DatabaseHelper.DESCRICAO}", produto.descricao)

        try {
           // escrita.execSQL(comandoSQL) // writable para escrita


            escrita.insert( // Aqui é um metodo da classe SQLiteOpenHelper
            /** Aqui ele espera receber 3 parametros, Tabela, coluna e valor */
                DatabaseHelper.TABELA_PRODUTOS,
                null, // Aqui só vamos precisar configurar quando não tiver nenhum valor sendo passado.
                valores

            )
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


//        val codigoSQL = "UPDATE produtos SET titulo = '${produto.titulo}' WHERE id_produto = 2 ;"

        val valores = ContentValues()
        valores.put("${DatabaseHelper.TITULO}", produto.titulo)
        valores.put("${DatabaseHelper.DESCRICAO}", produto.descricao)
        // Aqui precisamos do nosso argumento, que seria o id que vamos atualizar
        val args = arrayOf("${produto.id_produto}") /** Aqui sao os parametros que vai ser passado como argumento, eles vai entrar no lugar do ? */

        try {
            //escrita.execSQL(codigoSQL)




            escrita.update(
                /** Aqui é um metodo do SQLiteOpenHelper, mas ele espera receber 4 parametros
                 *  Tabela, ContentValues, Where e uma Where args
                 */

                DatabaseHelper.TABELA_PRODUTOS,
                valores,
                "id_produto = ?", // condiçao de oque vamos atualizar. Poderiamos passar 2 valores tambem ex: "id_produto = 1 AND qnt = 10",
                /** Seria como se fosse uma funçao com seus metodos, imagina que no args vamos ter 3 parametros, então o primeiro parametro vai ficar no primeiro ?
                 * O segundo parametro vai ficar no segundo ?
                 * O terceiro parametro vai ficar no 3 ?
                 */
                args



            )
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


        //val codigoSQL = "DELETE FROM produtos WHERE id_produto = $idProduto"

        val args = arrayOf( idProduto.toString() )

        try {
           // escrita.execSQL(codigoSQL)


            escrita.delete(
                /** Também espera receber alguns parametros
                 * 1 Tabela
                 * 2 Condição
                 */
                DatabaseHelper.TABELA_PRODUTOS,
                "${DatabaseHelper.ID_PRODUTO} = ?", // Lembrar que so temos uma condição la em cima, então la que vamos definir qual produto estamos removendo.
                args // Argumento de qual produto vamos remover.
            )


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