package com.allephnogueira.recuperandolista.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.recuperandolista.R
import com.allephnogueira.recuperandolista.api.ComentarioAPI
import com.allephnogueira.recuperandolista.api.PostagemAPI
import com.allephnogueira.recuperandolista.api.RetrofitHelper
import com.allephnogueira.recuperandolista.databinding.ActivityMainBinding
import com.allephnogueira.recuperandolista.model.Comentario
import com.allephnogueira.recuperandolista.model.Postagem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val retrofit by lazy {
        RetrofitHelper.retrofit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btnInicar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                recuperarPostagens()
            }
        }

        binding.btnUmaPostagem.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                recuperarUmaPostagem()
            }
        }

        binding.btnRecuperarOsComentarios.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                recuperarComentariosParaPostagem()
            }
        }


        binding.btnPostagensComQuery.setOnClickListener {
            // Recuperando comentarios com Query
            // Vamos recuperar os comentarios de uma postagem, no caso vai ficar como padrao postagem 1
            CoroutineScope(Dispatchers.IO).launch {
                recuperarComentariosDaPostagemComQuery()
            }

        }

        /** Quando o usuario clicar em publicar algo, vamos para uma nova activity para evitar sobrecarregar aqui */

        binding.btnNovaPostagem.setOnClickListener {
            val intent = Intent(this, PublicarActivity::class.java)
            startActivity(intent)
        }

        binding.btnAtualizarPostagem.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                //atualizarPostagemMetodoPUT()

                //atualizarPostagemmetodoPATCH()

                // Remover postagem

                removerPostagemMetodoDELETE()
            }
        }

    }

    private suspend fun removerPostagemMetodoDELETE() {
        var retorno : Response<Unit> ? = null

        try {
            val removerPostagem = retrofit.create(PostagemAPI::class.java)
            retorno = removerPostagem.removerPostagem(1)
        }catch (e: Exception){
            e.printStackTrace()
        }

        if (retorno != null && retorno.isSuccessful) {

            Log.i("info_postagemRemovida", "${retorno.code()} - Postagem removida com sucesso!")
        }
    }


    private suspend fun atualizarPostagemmetodoPATCH() {

        var retorno : Response<Postagem>? = null


        /* Criando a postagem */
        val postagem = Postagem(
            "Atualização da descrição!",
            -1,
            null,
            -1
        )


        try {
            /* Lembrar que o retrofit.create ele cria uma classe a partir da Interface
            Ai podemos usar seu metodo como se fosse realmente uma classe.
             */
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            retorno = postagemAPI.atualizarPostagemPatch(1, postagem)

        }catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_atualizarPostagem", "ERRO: Não consegui atualizar sua postagem")
        }


        /* Verificando se retorno esta tudo correto */

        if (retorno != null && retorno.isSuccessful){
            val postagem = retorno.body()
            val id = postagem?.id
            val titulo = postagem?.title
            val descricao = postagem?.descricao

            val descricaoCompleta = "ID: $id, Titulo: $titulo - Descrição: $descricao"

            Log.i("info_atualizarPostagem", descricaoCompleta)
        }
    }


    private suspend fun atualizarPostagemMetodoPUT() {

        var retorno : Response<Postagem>? = null


        /* Criando a postagem */
        val postagem = Postagem(
            "Atualizando postagem",
            -1,
            "Nova atualização",
            -1
            )


        try {
            /* Lembrar que o retrofit.create ele cria uma classe a partir da Interface
            Ai podemos usar seu metodo como se fosse realmente uma classe.
             */
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            retorno = postagemAPI.atualizarPostagem(1, postagem)

        }catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_atualizarPostagem", "ERRO: Não consegui atualizar sua postagem")
        }


        /* Verificando se retorno esta tudo correto */

        if (retorno != null && retorno.isSuccessful){
            val postagem = retorno.body()
            val id = postagem?.id
            val titulo = postagem?.title
            val descricao = postagem?.descricao

            val descricaoCompleta = "ID: $id, Titulo: $titulo - Descrição: $descricao"

            Log.i("info_atualizarPostagem", descricaoCompleta)
        }
    }

    private suspend fun recuperarComentariosDaPostagemComQuery() {
        var retorno : Response<List<Comentario>>? = null

        try {
            val ComentarioAPI = retrofit.create(ComentarioAPI::class.java)
            retorno = ComentarioAPI.recuperarComentariosParaPostagemQuery(1)
        }catch (e : Exception) {
            e.printStackTrace()
            Log.i("info_comentario", "ERRO: Comentario não localizado!")
        }

        if (retorno != null && retorno.isSuccessful) {
            val listaDeComentario = retorno.body()
            var comentariosJuntos = ""
            listaDeComentario?.forEach{

                val id = it.id
                val nome = it.name
                val email = it.email
                val comentario = it.comentario


                comentariosJuntos += "\nID: $id\n" +
                        "Nome: $nome\n" +
                        "E-mail: $email\n" +
                        "$comentario"

                withContext(Dispatchers.Main) {
                    binding.textComentario.text = comentariosJuntos
                }

                Log.i("info_comentarios", "$id - $comentario")

            }
        }
    }

    private suspend fun recuperarComentariosParaPostagem() {
        var retorno : Response<List<Comentario>>? = null

        try {
            val ComentarioAPI = retrofit.create(ComentarioAPI::class.java)
            retorno = ComentarioAPI.recuperarComentariosParaPostagem(1)
        }catch (e : Exception) {
            e.printStackTrace()
            Log.i("info_comentario", "ERRO: Comentario não localizado!")
        }

        if (retorno != null && retorno.isSuccessful) {
            val listaDeComentario = retorno.body()
            var comentariosJuntos = ""
            listaDeComentario?.forEach{

                val id = it.id
                val nome = it.name
                val email = it.email
                val comentario = it.comentario


                comentariosJuntos += "\nID: $id\n" +
                        "Nome: $nome\n" +
                        "E-mail: $email\n" +
                        "$comentario"

                withContext(Dispatchers.Main) {
                    binding.textComentario.text = comentariosJuntos
                }

                Log.i("info_comentarios", "$id - $comentario")

            }
        }
    }

    private suspend fun recuperarUmaPostagem() {
        var retorno: Response<Postagem>? = null

        try {
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            retorno = postagemAPI.recuperarPostagemUnica(2)
        }catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_postagem", "ERRO: Não encontramos essa postagem.")
        }

        if (retorno !=null && retorno.isSuccessful) {
            val postagemUnica = retorno.body()
            val resultado = "Titulo: ${postagemUnica?.title} \nDescrição: ${postagemUnica?.descricao}"

            withContext(Dispatchers.Main) {
                binding.textResultado.text = resultado
            }

            Log.i("info_postagem", resultado)
        }
    }

    private suspend fun recuperarPostagens() {
        /* Agora estamos colocando dentro de retorno uma lista de objetos */
        var retorno: Response<List<Postagem>>? = null
        val postagemDigitada = ""

        try {
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            retorno = postagemAPI.recuperarPostagens()

        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_postagens", "ERRO: Não consegui recuperar postagens!")
        }

        if (retorno != null && retorno.isSuccessful) {


            /* Postagem agora é uma lista de Objetos, e vamos pegar item por item */

            val listaPostagem = retorno.body()
            listaPostagem?.forEach { postagem ->
                val userID = postagem.userId
                val id = postagem.id
                val titulo = postagem.title
                val descricao = postagem.descricao

                Log.i(
                    "info_postagem",
                    "SUCESSO: userID: $userID - idPostagem: $id - titulo: $titulo - descrição: $descricao"
                )

            }


        }
    }
}