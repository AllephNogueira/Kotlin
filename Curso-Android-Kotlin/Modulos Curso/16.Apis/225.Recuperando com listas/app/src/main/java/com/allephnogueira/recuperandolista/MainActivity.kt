package com.allephnogueira.recuperandolista

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.recuperandolista.api.PostagemAPI
import com.allephnogueira.recuperandolista.api.RetrofitHelper
import com.allephnogueira.recuperandolista.databinding.ActivityMainBinding
import com.allephnogueira.recuperandolista.model.Postagem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val retrofit by lazy {
        RetrofitHelper.retrofit
    }

    private var postagem = mutableListOf<Postagem>()

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