package com.allephnogueira.sqliteandroid

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.sqliteandroid.database.DatabaseHelper
import com.allephnogueira.sqliteandroid.database.ProdutoDAO
import com.allephnogueira.sqliteandroid.databinding.ActivityMainBinding
import com.allephnogueira.sqliteandroid.model.Produto

class MainActivity : AppCompatActivity() {

    private val bancoDeDados by lazy {
        DatabaseHelper(this)
    }


    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {

            btnSalvar.setOnClickListener {
                salvar()
            }

            btnListarProdutos.setOnClickListener {
                listar()
            }


            btnAtualizar.setOnClickListener {
                atualizar()
            }

            btnRemover.setOnClickListener {
                removerProduto()
                Toast.makeText(applicationContext, "Produto removido com sucesso.", Toast.LENGTH_SHORT).show()
            }


        }


    }

    private fun removerProduto() {

        val idProduto = binding.editProduto.text.toString().toInt()
        val produtoDAO = ProdutoDAO(this)


        produtoDAO.remover(idProduto)



    }

    private fun atualizar() {
        /** Caso tenha duvida olhe o metodo de salvar **/

        val titulo = binding.editProduto.text.toString()
        val produtoDAO = ProdutoDAO(this)
        val produto = Produto (
            -1, titulo, "descricao produto..."
        )

        produtoDAO.atualizar(produto)

    }

    private fun listar() {
/**        val comandoSQL = "SELECT * FROM ${DatabaseHelper.TABELA_PRODUTOS};"


        val cursor = bancoDeDados.readableDatabase
            .rawQuery(
                comandoSQL,
                null) // Readable Database é para fazer a leitura do banco de dados.


        /**Dessa forma em vez de lembrar qual é cada indice, vamos passar o nome e o proprio kotlin vai passar o indice para a gente
         * Agora ele vai retornar para a gente os dados que estao dentro de produto, titulo e descricao
         * ATENCAO AQUI ESTA COM ERRO PQ DIGITEI O NOME DA TABELA ERRADO.*/

//        val indiceId = cursor.getColumnIndex("${DatabaseHelper.TABELA_PRODUTOS}") // Aqui estamos pegando o valor ja definido para evitar erros.
//        val indiceTitulo = cursor.getColumnIndex("${DatabaseHelper.TITULO}")
//        val indiceDescricao = cursor.getColumnIndex("${DatabaseHelper.DESCRICAO}")

        while ( cursor.moveToNext() ) {
            /** Cursor = Onde nosso mouse esta
             * Exemplo imagina os registros/linhas e nosso mouse em cima da linha 1
             * Então o cursor é isso é onde vai esta nosso mouse
             * moveToNext ele vai sair da primeira casa e vai avançar para a segunda, toda vez que ele avançar ele vai gerar um TRUE OU FALSE
             * Se for false ele para, pq é sinal que não tem mais registros
             *
             * Por isso estamos fazendo um while, pq enquanto for verdadeiro ele vai avançando...
             * MoveToNext ele começa sempre no primeiro registro e vai andando para baixo...
             */

            /** Recuperar as colunas/Registros
             * getInt porque estamos recuperando o ID e sabemos que o ID vai ser sempre um Int
             * No parametro vamos colocar a casa da coluna que vai se comportar como se fosse um array
             * Temos 3 colunas
             * id_produto = 0 / titulo = 1 / descrição = 2
             * */

            val idProduto = cursor.getInt(0)
            val tituloProduto = cursor.getString(1)
            val descricaoProduto = cursor.getString(2)

            Log.i("info_db", "id: $idProduto - titulo: $tituloProduto - descrição: $descricaoProduto")


            Log.i("info_db", "Posição do cursor = ${cursor.position}") // Contagem para saber quantos registros ele vai pegar...
        }

*/


        /** Agora sim, lembrando que ele vai retorna uma lista de produtos */
        val produtoDAO = ProdutoDAO(this)
        val listaProdutos = produtoDAO.listar()

        if (listaProdutos.isNotEmpty()) {
            /** Conferindo se a lista esta retornando alguma coisa, pq ela pode vir vazia */
            listaProdutos.forEach {
                Log.i("info_db", "${it.id_produto} - ${it.titulo}")
            }

        }
    }

    fun salvar() {
        /** Os metodos foram transferidos para o produtoDAO para poder organizar melhor o codigo.

        val produtoDigitado =
            binding.editProduto.text.toString() // Capturando oque o usuario digitou.
        val comandoSQL =
            "INSERT INTO ${DatabaseHelper.TABELA_PRODUTOS} (id_produto, titulo, descriacao) VALUES (null, '$produtoDigitado', 'Descrição...');"

        try {
            bancoDeDados.writableDatabase.execSQL(comandoSQL) // writable para escrita
            Log.i("info_db", "Produto cadastrado com sucesso.")

        } catch (e: Exception) {
            e.printStackTrace()
        }
        **/


        /** Capturando o titulo que o usuario escreveu */
        val titulo = binding.editProduto.text.toString()

        /** Instanciando o produtoDAO e passando o contexto */
        val produtoDAO = ProdutoDAO(this) // Devemos passar o contexto

        /** Criando nosso produto com os atributos **/
        val produto = Produto(
            -1,  // Isso é uma estrategia para quando nao queremos definir valor; Atenção o proprio banco de dados vai incrementar de 1 em 1
            titulo, // Estamos pegando o titulo que o proprio usuario escreveu
            "Produto novo com nota fiscal"

        )

        /** Salvando o nosso produto criado no banco de dados, utilizando o metodo que criamos para isso **/
        produtoDAO.salvar(produto) // Aqui agora chamamos o metodo passando o produto que criamos.
    }

}