package com.allephnogueira.recuperandolista.View

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.recuperandolista.R
import com.allephnogueira.recuperandolista.api.PostagemAPI
import com.allephnogueira.recuperandolista.api.RetrofitHelper
import com.allephnogueira.recuperandolista.databinding.ActivityPublicarBinding
import com.allephnogueira.recuperandolista.model.Postagem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class PublicarActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPublicarBinding.inflate(layoutInflater) }
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

        CoroutineScope(Dispatchers.IO).launch {
            salvarPostagemPorFormulario()
        }


    }

    private suspend fun salvarPostagem() {
        var retorno : Response<Postagem>? = null

        /* Aqui vamos passar os valores simulando que o usuario digitou para publicar
        * O id é gerado de forma automatica pela regra de negocio, então um padrao para ele sempre sera o -1 */

        val novaPostagem = Postagem(
            "Fui a praia",
            -1,
            "PRAIA ^^",
            1090
        )

        try {
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            retorno = postagemAPI.salvarPostagem(novaPostagem)

        }catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_postagem", "ERRO: Postagem não enviada.")
        }

        if (retorno != null && retorno.isSuccessful) {
            val postagemCriada = retorno.body()
            /*  Objetos retornados pela postagem  */
            val titulo = postagemCriada?.title
            val descricao = postagemCriada?.descricao

            /* Adicionando itens na tela

             */

            withContext(Dispatchers.Main) {
                binding.textTitulo.text = titulo
                binding.textPostagem.text = descricao
            }

        }else {
            Log.i("info_postagem", retorno?.code().toString())
        }


    }



    private suspend fun salvarPostagemPorFormulario() {
        var retorno : Response<Postagem>? = null

        /* Aqui vamos passar os dados que queremos salvar */


        val id = -1
        val userId = 1090
        val title = "Praia ^^"
        val descricao = "Partiu praia."

        try {
            val postagemAPI = retrofit.create(PostagemAPI::class.java)
            retorno = postagemAPI.salvarPostagemPorFomulario(
                userId, id, title, descricao
            )

        }catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_postagem", "ERRO: Postagem não enviada.")
        }

        if (retorno != null && retorno.isSuccessful) {
            val postagemCriada = retorno.body()
            /*  Objetos retornados pela postagem  */
            val titulo = postagemCriada?.title
            val descricao = postagemCriada?.descricao

            /* Adicionando itens na tela

             */

            withContext(Dispatchers.Main) {
                binding.textTitulo.text = titulo
                binding.textPostagem.text = descricao
            }

        }else {
            Log.i("info_postagem", retorno?.code().toString())
        }


    }
}